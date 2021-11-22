package T1.clustering;

import DO.ActivityFeatures;
import DO.Cluster;

import java.util.List;

public interface Clusterizer {
    List<Cluster> group(int k, List<ActivityFeatures> group);
}
