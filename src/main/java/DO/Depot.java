package DO;


import org.apache.commons.math3.ml.clustering.Clusterable;

import java.io.Serializable;

public class Depot implements Clusterable, Serializable {

    private Integer id;
    private Double X;
    private Double Y;
    private double Area;
    private double rent_min;
    private double rent_max;

    public Depot(Double X, Double Y) {
        this.X = X;
        this.Y = Y;

    }

    public Depot(Integer id, Double X, Double Y, double area, double rent_min, double rent_max) {
        this.id = id;
        this.X = X;
        this.Y = Y;
        this.Area = area;
        this.rent_min = rent_min;
        this.rent_max = rent_max;
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

    public void setX(Double x) {
        X = x;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double y) {
        Y = y;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        this.Area = area;
    }

    public double getRent_min() {
        return rent_min;
    }

    public void setRent_min(double rent_min) {
        this.rent_min = rent_min;
    }

    public double getRent_max() {
        return rent_max;
    }

    public void setRent_max(double rent_max) {
        this.rent_max = rent_max;
    }

    //CHECK, Lat = Y Long = X.. have to check this
    @Override
    public double[] getPoint() {
        return new double[]{this.X, this.Y};
    }


    //    public static Depot fromMathPoint(Integer id, double[] mathPoint, double area) {
//        return new Depot(id, mathPoint[0], mathPoint[1], area);
//    }
    public double distanceTo(Depot city2) {
        double x = Math.abs(this.getX() - city2.getX());
        double y = Math.abs(this.getY() - city2.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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

        Depot point = (Depot) obj;
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
