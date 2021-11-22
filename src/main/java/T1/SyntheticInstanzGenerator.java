package T1;

import DO.ActivityFeatures;
import DO.Cluster;
import DO.Demand;
import DO.Depot;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SyntheticInstanzGenerator {

    private final List<Rectangle2D.Double> listRect = new ArrayList<>();

    public List<Cluster> generate(double clusterFactor, int deliveryRegions, int customersNumber, int maxDemand, double HighRect, double WidthRect) {
        // generate 100x100 istances with customer coordinates (double) and random demand between 10-20 pieaces
        List<Cluster> clusters = new ArrayList<>();

        Rectangle2D.Double R = new Rectangle2D.Double(0.0, 0.0, WidthRect, HighRect);

        divide(R, deliveryRegions);
        List<Rectangle2D.Double> rect = listRect;

        // for even and not even number of rects in the lst
        int customersPerCluster;
        int rest = 0;
        if (customersNumber % rect.size() == 0) {
            customersPerCluster = customersNumber / rect.size();
        } else {
            rest = customersNumber % rect.size();
            customersPerCluster = customersNumber / rect.size();
        }

        for (int i = 0; i < rect.size(); i++) {
            //than add the rest customers to this cluster
            if ((i == rect.size() - 1) && rest != 0) {
                customersPerCluster = customersPerCluster + rest;
            }

            Rectangle2D.Double instance = rect.get(i);
            List<ActivityFeatures> customers = new ArrayList<>();
            double cntrX = instance.getCenterX();
            double cntrY = instance.getCenterY();
            //when clusterFactor == 0 -> the customers are evenly distributed in the rectangle area. So, the coordinates will be randomly generating within minX and maxX in there rectangle boundary
            // and when the clusterFactor == 1 -> it means that the customers are distributed around the centers.so that practically means, I take min and max boundaries around the center + - 3 Points
            double xMin_rand = instance.getMinX() + clusterFactor * (cntrX - instance.getMinX());
            double xMax_rand = instance.getMaxX() - clusterFactor * (instance.getMaxX() - cntrX);
            double yMin_rand = instance.getMinY() + clusterFactor * (cntrY - instance.getMinY());
            double yMax_rand = instance.getMaxY() - clusterFactor * (instance.getMaxY() - cntrY);

            for (int j = 0; j < customersPerCluster; j++) {
                int demand = getRandomInt(maxDemand - 10, maxDemand);
                double x = getRandomDouble(xMin_rand, xMax_rand);
                double y = getRandomDouble(yMin_rand, yMax_rand);
                ActivityFeatures acft = new ActivityFeatures(j, x, y, new Demand(demand));
                customers.add(acft);
            }
            Cluster km = new Cluster(new Depot(cntrX, cntrY), customers);
            clusters.add(km);
        }
        return clusters;
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private double getRandomDouble(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    private void divide(Rectangle2D.Double R, int k) {
        System.out.println(":::::::::::The Coordinates of the MAIN Rectangle --->>>" + " minX: " + R.getMinX() + " minY: " + R.getMinY()
                + "rightMaxX: " + R.getMaxX() + " rightMaxY: " + R.getMaxY());

        List<Rectangle2D.Double> setInMethod = new ArrayList<>();

        double w = R.getWidth();
        double h = R.getHeight();

        if (k == 1) {
            listRect.add(R);
            return;

        } else {
            int k1 = k / 2;
            int k2 = k % 2 + k1;

            if (w >= h) {
                //separate rectangle with vertical Line in two parts -> rectLeft and rectRight
                //factor alpha.actually the length of the bound alpha =(X2-X1)*anteilAufDerXAchse
                double withX = R.getBounds().getMaxX() - R.getBounds().getMinX();
                double alpha = (withX) * ((double) k2 / k);
                double alphaFromRightCorner = (withX) * (1.0 - ((double) k2 / k));
                Rectangle2D.Double rectLeft = new Rectangle2D.Double(R.getMinX(), R.getMinY(), alpha, R.getHeight());
                Rectangle2D.Double rectRight = new Rectangle2D.Double(R.getMaxX() - alphaFromRightCorner, R.getMinY(), alphaFromRightCorner, R.getHeight());

                // listRect.add(rectLeft);
                // listRect.add(rectRight);

                setInMethod.add(rectLeft);
                setInMethod.add(rectRight);
            } else {
                double highY = R.getBounds().getMaxY() - R.getBounds().getMinY();
                double alpha = (highY) * ((double) k2 / k);
                double alphaFromTopCorner = (highY) * (1.0 - ((double) k2 / k));
                Rectangle2D.Double rectBottom = new Rectangle2D.Double(R.getBounds().getMinX(), R.getBounds().getMinY(), R.getWidth(), alpha);
                Rectangle2D.Double rectTop = new Rectangle2D.Double(R.getBounds().getMinX(), R.getBounds().getMaxY() - alphaFromTopCorner, R.getWidth(), alphaFromTopCorner);

                setInMethod.add(rectBottom);
                setInMethod.add(rectTop);
            }
            //listRect.add(setInMethod.get(0));
            //listRect.add(setInMethod.get(1));
            divide(setInMethod.get(0), k2);
            divide(setInMethod.get(1), k - k2);
            return;
        }
    }
}
