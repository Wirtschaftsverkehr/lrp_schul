package T1;

import DO.ActivityFeatures;
import DO.Cluster;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args){

        double HighRect = 1000.0;
        double WidthRect = 1000.0;
        //InstanceGenerator instance1 = new InstanceGenerator(clusterFactor, clusterNumber, maxDemand, customernumber, HighRect, WidthRect);

        SyntheticInstanzGenerator sig = new SyntheticInstanzGenerator();
        List<Cluster> kmeans = sig.generate(0.3, 4, 1000,  30, HighRect, WidthRect);
        System.out.println("Instance generated");

        List<ActivityFeatures> activities = kmeans.stream()
                .map(Cluster::getGroup)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // Aufgabe: clusterisiere die Kunden mit Hilfe von K-Means Clustering und Fuzzy-Clustering
        // Plotte die Ergebnisse mit der Klasse "Plotter"



    }

}
