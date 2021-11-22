package T1.clustering;


import DO.ActivityFeatures;
import DO.Cluster;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.FuzzyKMeansClusterer;

import java.util.List;
import java.util.stream.Collectors;

public class FuzzyKMeans implements Clusterizer {

    static public double[][] data;
    double Fuzziness = 1.01;

    public FuzzyKMeans(double fuzziness) {
        Fuzziness = fuzziness;
    }

    @Override
    public List<Cluster> group(int k, List<ActivityFeatures> group) {
        FuzzyKMeansClusterer fuzzyClusterer = new FuzzyKMeansClusterer(k, Fuzziness);
        List<CentroidCluster<ActivityFeatures>> cluster = fuzzyClusterer.cluster(group);
        //RealMatrix membership = fuzzyClusterer.getMembershipMatrix();
        //data = membership.getData();
        //create newDepotFromClusterList
        List<Cluster> clustered = cluster.stream().map(Cluster::fromCluster).collect(Collectors.toList());
        return clustered;
    }
}
