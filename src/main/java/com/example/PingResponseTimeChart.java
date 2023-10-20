package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
public class PingResponseTimeChart {

    private JFrame frame;
    private XYSeries series;
    private Timer timer;
    private static final int MAX_DATA_POINTS = 50;
    private boolean connectionLost = false;
    public String targetIp; // Cambia esto a la IP que deseas monitorear

    public PingResponseTimeChart(String targetIp) {
        this.targetIp = targetIp;
        frame = new JFrame("Tiempo de Respuesta del Ping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 400));

        series = new XYSeries("Tiempo de Respuesta (ms)");
        // Cambios en la inicialización del gráfico
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Tiempo de Respuesta del Ping", "Tiempo", "Respuesta (ms)", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();
        XYSplineRenderer renderer = new XYSplineRenderer();

        plot.getRangeAxis().setRange(-150, 150);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesShapesVisible(0, false);

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double pingLoss = pingLoss(targetIp);
                if (pingLoss > 5.0) { // Si la pérdida de paquetes es mayor al 5%, considera que la conexión se perdió
                    connectionLost = true;
                }
                if (connectionLost) {
                    System.out.println("ACCION SMS");
                    System.out.println("LOG DE ESTADO");
                     renderer.setSeriesPaint(0, Color.RED);
                     plot.setRenderer(renderer);
                    series.addOrUpdate(System.currentTimeMillis(), 100.0); // Pérdida de conexión al 100%
                } else {
                    series.addOrUpdate(System.currentTimeMillis(), 0);
                }
            }
        });
        timer.start();
    }

   private double pingLoss(String targetIp) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "-n", "1", targetIp); // Realiza 5 intentos de ping
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String pingOutput = "";

            while ((line = reader.readLine()) != null) {
                pingOutput += line + "\n";
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                // Analizar la salida del ping para obtener el porcentaje de pérdida de paquetes
                Pattern pattern = Pattern.compile("(\\d+)% perdidos");
                Matcher matcher = pattern.matcher(pingOutput);

                if (matcher.find()) {
                    return Double.parseDouble(matcher.group(1));
                }
            }

            // El ping falló o no se pudo analizar la salida, calcula la pérdida de paquetes como 100%
            return 100.0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0.0; // Error en la ejecución del ping
        }
    }

   
}
