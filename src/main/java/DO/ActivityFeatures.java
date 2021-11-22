package DO;

import org.apache.commons.math3.ml.clustering.Clusterable;

import java.io.Serializable;

/**
 * object for all customers with main features;
 */
public class ActivityFeatures implements Clusterable, Serializable {


    // Attention For ESRI its almost always going to be:
    //Lat = Y Long = X. So, Basically I need to change all x y coordinates in my code...but it works so far, so.please don't touch

    private String shpName;
    private Integer id;
    private Double X;//Lat = Y
    private Double Y;  // Long = X
    private String area;
    private Demand demand;
    private Integer DepotIDReal;

    public ActivityFeatures() {
    }

    public ActivityFeatures(Double X, Double Y) {
        this.X = X;
        this.Y = Y;
    }

    public ActivityFeatures(Integer id, Double X, Double Y, Demand demand) {
        this.id = id;
        this.X = X;
        this.Y = Y;
        this.demand = demand;
    }

    public ActivityFeatures(String shpName, Integer id, Double X, Double Y, String area, Integer depotIDReal) {
        this.shpName = shpName;
        this.id = id;
        this.X = X;
        this.Y = Y;
        this.area = area;
        this.DepotIDReal = depotIDReal;
    }

    public ActivityFeatures(String shpName, Integer id, Double X, Double Y, String area, Demand demand, Integer depotIDReal) {
        this.shpName = shpName;
        this.id = id;
        this.X = X;
        this.Y = Y;
        this.area = area;
        this.demand = demand;
        this.DepotIDReal = depotIDReal;
    }

    public String getShpName() {
        return shpName;
    }

    public void setShpName(String shpName) {
        this.shpName = shpName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getX() {
        return X;
    }

    public void setX(Double X) {
        this.X = X;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double Y) {
        this.Y = Y;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public Integer getDepotIDReal() {
        return DepotIDReal;
    }

    public void setDepotIDReal(Integer depotIDReal) {
        DepotIDReal = depotIDReal;
    }

    //CHECK if , Lat = Y Long = X..check this later

    @Override
    public double[] getPoint() {
        return new double[]{this.X, this.Y};
    }

    public double distanceTo(ActivityFeatures city2) {
        double x = Math.abs(this.getX() - city2.getX());
        double y = Math.abs(this.getY() - city2.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        ActivityFeatures point = (ActivityFeatures) obj;
        return (X == point.X
                || (X != null && X.equals(point.getX())))
                && (Y == point.Y
                || (Y != null && Y.equals(point.getY())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((X == null) ? 0 : X.hashCode());
        result = prime * result
                + ((Y == null) ? 0 : Y.hashCode());
        return result;
    }
}
