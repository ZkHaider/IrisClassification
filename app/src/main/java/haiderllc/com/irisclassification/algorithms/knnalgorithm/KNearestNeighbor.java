package haiderllc.com.irisclassification.algorithms.knnalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import haiderllc.com.irisclassification.models.Iris;
import haiderllc.com.irisclassification.sampledata.SampleData;

/**
 * Created by Haider on 9/24/2014.
 */
public class KNearestNeighbor {

    public static final String TAG = "KNearestNeighbor";
    private Iris query;
    private int neighbors;
    private ArrayList<Iris> trainingArray;
    private ArrayList<Iris> distanceArray = new ArrayList<Iris>(); // Distance array which will be sorted from least to greatest

    /*
    Constructor for the KNN algorithm, which takes in the query point and then calculates the
    euclidean distance between all training examples.
     */
    public KNearestNeighbor(Iris query, int neighbors) {
        this.query = query;
        this.neighbors = neighbors;

        initData();


        calculateNeighbors(SampleData.generateTrainingList(), query);
        display();
        sort();
        display();
    }

    /*
    Calculates the euclidean distance, grabs training data from MachineLearningInstance class
     */
    protected void calculateNeighbors(ArrayList<Iris> trainingSamples, Iris query) {

        for (int i = 0; i < trainingSamples.size(); i++) {
            // Store the distance of that Iris item with its id and distance into the
            // distanceArray.
            distanceArray.add(i, new Iris(
                    trainingSamples.get(i).getId(), query.calculateDistance(trainingSamples.get(i))
                    , trainingSamples.get(i).getIrisClassName()));

        }
    }

    /*
    Method to return the distanceArray with its Iris Objects and id
     */
    public ArrayList<Iris> getDistanceArray() {
        return distanceArray;
    }

    public void display() {
        for (int i = 0; i < 10; i++) {
//            Log.i("KNearestNeighbor", "ID: " + distanceArray.get(i).getDistanceId());
//            Log.i("KNearestNeighbor", "ID: " + distanceArray.get(i).getDistance());
        }
    }

    private void initData() {
        trainingArray = new ArrayList<Iris>();
        // Take data from SampleData and store it into trainingArray
        for (int i = 0; i < SampleData.generateTrainingList().size(); i++) {
            trainingArray.add(i, SampleData.generateTrainingList().get(i));
        }
    }


    public void sort() {
        Collections.sort(distanceArray);
    }

}
