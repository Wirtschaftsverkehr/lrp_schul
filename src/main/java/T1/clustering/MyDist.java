package T1.clustering;

import DO.ActivityFeatures;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

import java.util.ArrayList;
import java.util.List;


public class MyDist implements DistanceMeasure {

    List<ActivityFeatures> customers = new ArrayList<>();

    public MyDist(List<ActivityFeatures> customers) {
        this.customers = customers;
    }

    private double calculateDist(double[] a, double[] b) {
//        int demand =1;
//        int i = 0;
//        while (customers.get(i).getPoint()[0] == a[0] && customers.get(i).getPoint()[1] == a[1]) {
//            demand = customers.get(i).getDemand();
//            i++;
//        }


        double A = a[0] - b[0];
        double B = a[1] - b[1];
        return Math.sqrt((A * A) + (B * B));

    }

    @Override
    public double compute(double[] a, double[] b) {

        return calculateDist(a, b);

    }

}
