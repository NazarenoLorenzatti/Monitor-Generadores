//package com.example;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.renderer.category.AreaRenderer;
//import org.jfree.chart.title.TextTitle;
//import org.jfree.chart.title.Title;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.util.Random;
//
//public class PacketLossChart {
//    private JFrame frame;
//    private DefaultCategoryDataset dataset;
//    private Timer timer;
//    private int dataCount = 0;
//    private String targetIp = "45.230.65.210";
//
//    public PacketLossChart() {
//        frame = new JFrame("Pérdida de Paquetes");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(800, 400));
//
//        dataset = new DefaultCategoryDataset();
//
//        JFreeChart chart = createChart(dataset);
//
//        ChartPanel chartPanel = new ChartPanel(chart);
//        frame.add(chartPanel);
//
//        timer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                double packetLoss = getPingPacketLoss(targetIp);
//                dataset.addValue(packetLoss, "Porcentaje", Integer.toString(dataCount));
//                dataCount++;
//
//                // Puedes ajustar la cantidad de datos que deseas mostrar en el gráfico
//                if (dataCount > 75) {
//                    dataset.removeValue("Porcentaje", Integer.toString(dataCount - 51));
//                }
//            }
//        });
//        timer.start();
//    }
//    
//     private double getPingPacketLoss(String targetIp) {
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
//
//    private double generateRandomPacketLoss() {
//        // Simula una pérdida de paquetes aleatoria (reemplaza con tu lógica real)
//        Random random = new Random();
//        return random.nextDouble() * 10; // Valores de ejemplo entre 0% y 10%
//    }
//
//    private JFreeChart createChart(DefaultCategoryDataset dataset) {
//        JFreeChart chart = ChartFactory.createAreaChart(
//                "Pérdida de Paquetes", "Tiempo", "Porcentaje", dataset,
//                PlotOrientation.VERTICAL, true, true, false);
//
//        CategoryPlot plot = chart.getCategoryPlot();
//        AreaRenderer renderer = new AreaRenderer();
//        plot.setRenderer(renderer);
//
//        Title subtitle = new TextTitle("Últimos 50 valores"); // Puedes personalizar el título
//        chart.addSubtitle(subtitle);
//
//        return chart;
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