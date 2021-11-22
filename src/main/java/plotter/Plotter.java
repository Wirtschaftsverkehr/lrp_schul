package plotter;

import DO.ActivityFeatures;
import DO.Cluster;
import DO.Depot;
import org.math.plot.Plot2DPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Plotter {


    public Plotter() {

    }


    public static void plot(List<Cluster> groupWithDepots, String path) {
        Plot2DPanel plot = new Plot2DPanel();
        Random rand = new Random();

        groupWithDepots.forEach(nodes -> {
            // add a line plot to the PlotPanel
            //plot.addScatterPlot("center point", Color.RED, new double[][]{nodes.getCenter().getPoint()});

            int randoms = rand.nextInt(100);
            int randoms2 = rand.nextInt(255);
            int randoms3 = rand.nextInt(255);
            List<ActivityFeatures> group = nodes.getGroup();
            double[][] groupArr = new double[nodes.getGroup().size()][2];
            for (int i = 0; i < groupArr.length; i++) {
                double X = group.get(i).getX();
                double Y = group.get(i).getY();
                groupArr[i][0] = X;
                groupArr[i][1] = Y;
            }
            plot.addScatterPlot("points", new Color(randoms, randoms3, randoms2), groupArr);
            //plot.addScatterPlot("center point", Color.darkGray, new double[][]{nodes.getCenter().getPoint()});
            //plot.addScatterPlot("center point", Color.RED, new double[][]{nodes.getCenter().getPoint()});

        });
        List<Depot> depots = groupWithDepots.stream().map(Cluster::getCenter).collect(Collectors.toList());
        double[][] groupDepots = new double[depots.size()][2];
        for (int i = 0; i < groupDepots.length; i++) {
            double X = depots.get(i).getX();
            double Y = depots.get(i).getY();
            groupDepots[i][0] = X;
            groupDepots[i][1] = Y;
        }
        plot.addScatterPlot("center point", Color.RED, groupDepots);

        System.out.println("PLOT SIZE"  + groupWithDepots.size());

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setPreferredSize(new Dimension(800, 800));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
//        try {
//            Thread.sleep(1000); // was thread.sleep(1000)
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Component panel = frame.getContentPane();
        frame.pack();
        BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(img, "png", new File(path));
            System.out.println("panel saved as image");

        } catch (Exception e) {
            System.out.println("panel not saved" + e.getMessage());
        }
        frame.dispose();
    }

    public void plot(List<ActivityFeatures> points, List<Depot> depots, String path) {
        Plot2DPanel plot = new Plot2DPanel();
        depots.forEach(nodes -> {
            // add a line plot to the PlotPanel
            plot.addScatterPlot("center point", Color.RED, new double[][]{nodes.getPoint()});

        });
        points.forEach(initialPoints -> {
            plot.addScatterPlot("point", Color.BLACK, new double[][]{initialPoints.getPoint()});
        });

        //
////        // put the PlotPanel in a JFrame, as a JPanel
//        JFrame frame = new JFrame("a plot panel");
//        frame.(plot.getComponents());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 500);
//        frame.setVisible(true);
//
        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//        try {
//            Thread.sleep(50000); // was thread.sleep(1000)
//        } catch (
//                InterruptedException e) {
//            e.printStackTrace();
//        }

        Component panel = frame.getContentPane();
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        panel.paint(img.getGraphics());
        try {
            ImageIO.write(img, "png", new File(path));
            System.out.println("panel saved as image");

        } catch (
                Exception e) {
            System.out.println("panel not saved" + e.getMessage());
        }
    }

}


