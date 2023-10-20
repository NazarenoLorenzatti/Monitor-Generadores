//package com.example;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.XYPlot;
//import org.jfree.chart.renderer.xy.XYSplineRenderer;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.net.InetAddress;
//
//public class NewClass {
//    private JFrame frame;
//    private XYSeries series;
//    private Timer timer;
//    private boolean connectionLost = false;
//    private String targetIp = "10.20.30.201"; // Cambia esto a la IP que deseas monitorear
//
//    public NewClass() {
//        frame = new JFrame("Pérdida de Paquetes");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(800, 400));
//
//        series = new XYSeries("Porcentaje de Pérdida");
//        XYSeriesCollection dataset = new XYSeriesCollection(series);
//
//        JFreeChart chart = ChartFactory.createXYLineChart(
//                "Pérdida de Paquetes", "Tiempo", "Porcentaje", dataset,
//                PlotOrientation.VERTICAL, true, true, false);
//
//        XYPlot plot = chart.getXYPlot();
//        XYSplineRenderer renderer = new XYSplineRenderer();
//        plot.setRenderer(renderer);
//
//        ChartPanel chartPanel = new ChartPanel(chart);
//        frame.add(chartPanel);
//
//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                double packetLoss = getPingPacketLoss(targetIp);
//                if (packetLoss > 5.0) { // Si la pérdida de paquetes es mayor al 5%, considera que la conexión se perdió
//                    connectionLost = true;
//                }
//                if (connectionLost) {
//                    series.addOrUpdate(System.currentTimeMillis(), 100.0); // Pérdida de conexión al 100%
//                } else {
//                    series.addOrUpdate(System.currentTimeMillis(), packetLoss);
//                }
//            }
//        });
//        timer.start();
//    }
//
//    private double getPingPacketLoss(String targetIp) {
//        try {
//            InetAddress inet = InetAddress.getByName(targetIp);
//
//            // Ejecutar un ping a la dirección IP y analizar la respuesta
//            Process process = Runtime.getRuntime().exec("ping -c 5 " + targetIp); // Realiza 5 intentos de ping
//            int exitCode = process.waitFor();
//
//            if (exitCode == 0) {
//                // Ping exitoso, no hay pérdida de paquetes
//                return 0.0;
//            } else {
//                // El ping falló, calcula la pérdida de paquetes
//                return 100.0; // Pérdida de paquetes al 100%
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//            return 0.0; // Error en la ejecución del ping
//        }
//    }
//
//    public void run() {
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new PacketLossChart().run();
//            }
//        });
//    }
//}
