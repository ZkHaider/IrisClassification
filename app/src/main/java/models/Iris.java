package models;

/**
 * Created by Haider on 9/24/2014.
 */

/*
Class Description: The Iris object saves the ID of the object, the sepal length and width and the
    petal length and width from the .data file which is implemented inside the MachineLearningInstance
    class. This model class also has a method for calculating the euclidean distance between two iris objects
    which is then implemented inside the KNearestNeighbor class.
 */

public class Iris implements Comparable<Iris> {


    private int id;
    private int distanceId;
    private double distance;
    private String sepalLengthCm;
    private String sepalWidthCm;
    private String petalLengthCm;
    private String petalWidthCm;
    private String irisClassName;

    /*
    Initial Constructor for the Iris item
     */
    public Iris(int id, String sepalLengthCm, String sepalWidthCm, String petalLengthCm,
         String petalWidthCm, String irisClassName) {
        this.id = id;
        this.sepalLengthCm = sepalLengthCm;
        this.sepalWidthCm = sepalWidthCm;
        this.petalLengthCm = petalLengthCm;
        this.petalWidthCm = petalWidthCm;
        this.irisClassName = irisClassName;
    }

    /*
    Iris type for calculating the distance between two iris items
     */
    public Iris(int id, double distance, String irisClassName) {
        this.distanceId = id;
        this.distance = distance;
        this.irisClassName = irisClassName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSepalLengthCm() {
        return sepalLengthCm;
    }

    public void setSepalLengthCm(String sepalLengthCm) {
        this.sepalLengthCm = sepalLengthCm;
    }

    public String getSepalWidthCm() {
        return sepalWidthCm;
    }

    public void setSepalWidthCm(String sepalWidthCm) {
        this.sepalWidthCm = sepalWidthCm;
    }

    public String getPetalLengthCm() {
        return petalLengthCm;
    }

    public void setPetalLengthCm(String petalLengthCm) {
        this.petalLengthCm = petalLengthCm;
    }

    public String getPetalWidthCm() {
        return petalWidthCm;
    }

    public void setPetalWidthCm(String petalWidthCm) {
        this.petalWidthCm = petalWidthCm;
    }

    public String getIrisClassName() {
        return irisClassName;
    }

    public void setIrisClassName(String irisClassName) {
        this.irisClassName = irisClassName;
    }

    public int getDistanceId() {
        return distanceId;
    }

    public void setDistanceId(int distanceId) {
        this.distanceId = distanceId;
    }

    /*
    Calculate the distances between iris types and return that distance
    Distance includes differences between values of the sepal length and width and petal length and width.
     */
    public double calculateDistance(Iris otherIris) {
        double d1 = Math.pow(Double.parseDouble(this.sepalLengthCm) - Double.parseDouble(otherIris.sepalLengthCm),2);
        double d2 = Math.pow(Double.parseDouble(this.sepalLengthCm) - Double.parseDouble(otherIris.sepalLengthCm),2);
        double d3 = Math.pow(Double.parseDouble(this.sepalLengthCm) - Double.parseDouble(otherIris.sepalLengthCm),2);
        double d4 = Math.pow(Double.parseDouble(this.sepalLengthCm) - Double.parseDouble(otherIris.sepalLengthCm),2);
        distance = Math.sqrt(d1+d2+d3+d4);
        return distance;
    }

    public double getDistance() {
        return distance;
    }

    /*
    Comparator method which will sort out the Iris objects according to their distance.
     */
    public int compareTo(Iris other) {
        return new Double(this.distance).compareTo(new Double(other.distance));
    }

}
