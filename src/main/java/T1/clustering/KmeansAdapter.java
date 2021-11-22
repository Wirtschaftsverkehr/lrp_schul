package T1.clustering;

import DO.ActivityFeatures;
import DO.Cluster;
import DO.Depot;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class KmeansAdapter {
    private List<Cluster> kmeans = new ArrayList<>();

    public KmeansAdapter(List<Cluster> kmeans) {
        this.kmeans = kmeans;
    }

    //looks for a nearest depot and assign it to cluster
    public List<Cluster> swap(List<Depot> origins) {

        List<Cluster> newList = new ArrayList<>();
        for (int i = 0; i < kmeans.size(); i++) {
            Depot centr = kmeans.get(i).getCenter(); // center of a cluster
            List<ActivityFeatures> acft = kmeans.get(i).getGroup();
            TreeMap<Double, Depot> mindist = origins.stream()
                    .collect(Collectors.toMap(centr::distanceTo, b -> b, (oldValue, newValue) -> oldValue, TreeMap::new));
            Depot minDistDepot = mindist.firstEntry().getValue(); //nearest depot to this cluster center
            //swap the center with the nearest depot in this cluster
            Cluster kmcativities = new Cluster(minDistDepot, acft);
            newList.add(kmcativities);
        }
        return newList;
    }

}
