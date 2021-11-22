package T1.clustering;

import DO.ActivityFeatures;
import DO.Cluster;
import DO.Depot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//For s(i) to be close to 1 we require a(i) , b(i).
// As a(i) is a measure of how dissimilar i is to its own cluster,
// a small value means it is well matched. Furthermore, a large b(i) implies
// that i is badly matched to its neighbouring cluster.
// Thus an s(i) close to one means that the data is appropriately clustered.
// If s(i) is close to negative one, then by the same logic we see that
// i would be more appropriate if it was clustered in its neighbouring cluster.
// An s(i) near zero means that the data is on the border of two natural clusters.
// @Wikipedia
public class SilhouetteIndex {
    private List<Cluster> kmeansCenterActivities = new ArrayList<>();

    public SilhouetteIndex(List<Cluster> kmeansCenterActivities) {
        this.kmeansCenterActivities = kmeansCenterActivities;
    }

    public double calculateIndex() {
        double averageSillhuetteIndex = 0.0;
        List<Double> sillhuetteIndexes = new ArrayList<>();
        //System.out.println("New Instance !!!");

        for (Cluster cluster : kmeansCenterActivities) {

            // System.out.println("Cluster : " + cluster.getCenter().getId());
            Depot warehouse = cluster.getCenter();
            List<ActivityFeatures> shops = cluster.getGroup();
            List<Double> custX = shops.stream().map(ActivityFeatures::getX).collect(Collectors.toList());
            List<Double> custY = shops.stream().map(ActivityFeatures::getY).collect(Collectors.toList());

            //calculate  the mean distance between i and all other data points in the same cluster,
//            double[][] dismatrix = computeDistanceMatrix(custX, custY);
//            double sumDist = 0.0;
//            for (int i = 0; i < dismatrix.length; i++) {
//                for (int j = i + 1; j < dismatrix[i].length; j++) {
//                    sumDist = sumDist + dismatrix[i][j];
//                }
//            }
            //for each point in cluster i we compute a distance to all other points in all other cluster which doesnt belong to this cluster i
            for (ActivityFeatures currentPoint : shops) {
                double currentX = currentPoint.getX();
                double currentY = currentPoint.getY();

                double S_i = Double.MAX_VALUE;

                double a_i = 0.0;
                for (int i = 0; i < custX.size(); i++) {
                    double thisX = custX.get(i);
                    double thisY = custY.get(i);

                    a_i = a_i + computeDist(currentX, thisX, currentY, thisY);
                }
                double a = a_i / (shops.size() - 1);
                if (Double.isNaN(a_i)) a = Double.MAX_VALUE;
                //System.out.println("a: " + a);

                List<Double> b_Set = new ArrayList<>();

                //find closest cluster
                for (Cluster otherClusters : kmeansCenterActivities) {
                    if (otherClusters.getCenter().equals(warehouse)) continue;

                    List<ActivityFeatures> secondShop = otherClusters.getGroup();
                    List<Double> secondX = secondShop.stream().map(ActivityFeatures::getX).collect(Collectors.toList());
                    List<Double> secondY = secondShop.stream().map(ActivityFeatures::getY).collect(Collectors.toList());
                    double distB = 0.0;

                    for (int i = 0; i < secondX.size(); i++) {
                        double nextX = secondX.get(i);
                        double nextY = secondY.get(i);

                        distB = distB + computeDist(currentX, nextX, currentY, nextY);
                    }
                    //calculate  the mean distance between i and all other data points in OTHER clusters
                    double b_i = distB / secondShop.size();
                    b_Set.add(b_i);
                }
                //neighborhoods cluster
                double b = Collections.min(b_Set);
               // System.out.println("b: " + b);

                if (shops.size() == 1 || a == b) {
                    S_i = 0.0;
                    sillhuetteIndexes.add(S_i);
                    continue;
                }
                S_i = (b - a) / Math.max(b, a);
                // System.out.println("S_i: " + S_i);
                sillhuetteIndexes.add(S_i);
            }
        }

        //return Sillhuette average of all observations
        if (!sillhuetteIndexes.isEmpty()) {
            averageSillhuetteIndex = sillhuetteIndexes.stream().mapToDouble(d -> d).average().getAsDouble();
        }
        return averageSillhuetteIndex;
    }


//    // Computes the distance matrix
//    private double[][] computeDistanceMatrix(List<Double> customersX, List<Double> customersY) {
//        int nbCustomers = customersX.size();
//        double[][] distanceMatrix = new double[nbCustomers][nbCustomers];
//
//        for (int i = 0; i < nbCustomers; i++) {
//            distanceMatrix[i][i] = 0;
//            for (int j = i + 1; j < nbCustomers; j++) {
//                double dist = computeDist(customersX.get(i), customersX.get(j), customersY.get(i), customersY.get(j));
//                distanceMatrix[i][j] = dist;
//                distanceMatrix[j][i] = dist;
//            }
//        }
//        return distanceMatrix;
//    }

    private double computeDist(double xi, double xj, double yi, double yj) {
        return Math.sqrt(Math.pow(xi - xj, 2) + Math.pow(yi - yj, 2));
    }
}
