package DO;



import org.apache.commons.math3.ml.clustering.CentroidCluster;

import java.io.Serializable;
import java.util.List;

public class Cluster implements Serializable {
    private Depot center;
    private List<ActivityFeatures> group;


    public Cluster(Depot center, List<ActivityFeatures> group) {
        this.center = center;
        this.group = group;
    }

    public Depot getCenter() {
        return center;
    }

    public void setCenter(Depot center) {
        this.center = center;
    }

    public List<ActivityFeatures> getGroup() {
        return group;
    }

    public void setGroup(List<ActivityFeatures> group) {
        this.group = group;
    }

    public static Cluster fromCluster(CentroidCluster<ActivityFeatures> eachCluster) {

        double[] cntr = eachCluster.getCenter().getPoint();
        List<ActivityFeatures> act = eachCluster.getPoints();
        //act.stream().forEach(a-> a.setDepotIDReal(depotID)); //here the clusterId can be set in
        Depot center = new Depot(cntr[0], cntr[1]);
        return new Cluster(center, act);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Cluster point = (Cluster) obj;
        return (this.center.getX().equals(point.getCenter().getX())
                || (this.center.getX() != null && this.center.getX().equals(point.getCenter().getX())))
                && (this.center.getY().equals(point.getCenter().getY())
                || (this.center.getY() != null && this.center.getY().equals(point.getCenter().getY())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.center.getX() == null) ? 0 : this.center.getX().hashCode());
        result = prime * result
                + ((this.center.getY() == null) ? 0 : this.center.getY().hashCode());
        return result;
    }

}
