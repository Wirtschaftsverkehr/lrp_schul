package T1.clustering;

import DO.ActivityFeatures;
import DO.Cluster;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class KMeansPlusPlus implements Clusterizer{

    @Override
    public List<Cluster> group(int k, List<ActivityFeatures> group) {
        KMeansPlusPlusClusterer<ActivityFeatures> kMeansPlusPlusClusterer = new KMeansPlusPlusClusterer<>(k,
                1000, new MyDist((List<ActivityFeatures>) group));
        List<CentroidCluster<ActivityFeatures>> cluster = kMeansPlusPlusClusterer.cluster((Collection<ActivityFeatures>) group);
        //create newDepotFromClusterList
        List<Cluster> clustered = cluster.stream().map(Cluster::fromCluster).collect(Collectors.toList());
        return clustered;
    }
}
