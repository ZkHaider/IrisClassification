package haiderllc.com.irisclassification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import knnalgorithm.KNearestNeighbor;
import models.Iris;
import sampledata.SampleData;

/**
 * Created by Haider on 10/1/2014.
 */
public class LoadingClass extends Activity {

    private ArrayList<Iris> trainingSet = new ArrayList<Iris>();
    private ArrayList<Iris> testSet = new ArrayList<Iris>();
    private ArrayList<Iris> neighborArray = new ArrayList<Iris>();
    private KNearestNeighbor kNearestNeighbor;
    private long id;
    private int neighbors = 5;

    private ImageView imageView;
    private TextView className;
    private String resultClassName;

    private final String irisSetosa = "Iris-setosa";
    private final String irisVersicolor = "Iris-versicolor";
    private final String irisVirginica = "Iris-virginica";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        imageView = (ImageView) findViewById(R.id.decisionImage);
        className = (TextView) findViewById(R.id.className);

        // Load Training Set
        loadTrainingSet();
        loadTestSet();

        // Grab ListView Item position
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        id = b.getLong("id");

        algorithmResult();

        Toast.makeText(this, resultClassName, Toast.LENGTH_LONG).show();

        // Setup logic for imageview
        if (resultClassName == irisSetosa) {
            imageView.setImageResource(R.drawable.iris_setosa);
        } else if (resultClassName == irisVirginica) {
            imageView.setImageResource(R.drawable.iris_virginica);
        } else if (resultClassName == irisVersicolor) {
            imageView.setImageResource(R.drawable.iris_versicolor);
        }

        // Setup logic for textview
        if (resultClassName == irisSetosa) {
            className.setText(irisSetosa);
        } else if (resultClassName == irisVersicolor) {
            className.setText(irisVersicolor);
        } else if (resultClassName == irisVirginica) {
            className.setText(irisVirginica);
        }

    }

    private void loadTrainingSet() {
        trainingSet = SampleData.generateTrainingList();
    }

    private void loadTestSet() {
        testSet = SampleData.generateTestList();
    }

    public String algorithmResult() {
        resultClassName = "";
        int irisSetosaCounter = 0;
        int irisVersicolorCounter = 0;
        int irisVirginicaCounter = 0;

        /*
        Arbitrarly pick 5-k nearest neighbors
         */
        kNearestNeighbor = new KNearestNeighbor(testSet.get((int) id), neighbors);

        neighborArray = kNearestNeighbor.getDistanceArray();

        if (neighborArray.isEmpty()) {
            Toast.makeText(this, "NeighborArray is empty!", Toast.LENGTH_LONG).show();
        }

        // Write in logic to determine the class name
        // Loop around neighbor array using the neighbors value
        for (int i = 0; i < neighbors; i++) {

            if (neighborArray.get(i).getIrisClassName() == irisSetosa) {
                irisSetosaCounter++;
            } else if (neighborArray.get(i).getIrisClassName() == irisVersicolor) {
                irisVersicolorCounter++;
            } else if (neighborArray.get(i).getIrisClassName() == irisVirginica) {
                irisVirginicaCounter++;
            } else {
                // Do nothing
            }
        }

        /*
        Churning out 0's for the counters, FIX this issue figure out why.
         */

        Toast.makeText(this, String.valueOf(irisSetosaCounter), Toast.LENGTH_LONG).show();
        Toast.makeText(this, String.valueOf(irisVersicolorCounter), Toast.LENGTH_LONG).show();
        Toast.makeText(this, String.valueOf(irisVirginicaCounter), Toast.LENGTH_LONG).show();

        // Setup logic for determining what is the best fit class name for the query
        if (irisSetosaCounter > irisVersicolorCounter && irisSetosaCounter > irisVirginicaCounter) {
            resultClassName = irisSetosa;
        } else if (irisVersicolorCounter > irisSetosaCounter && irisVersicolorCounter > irisVirginicaCounter) {
            resultClassName = irisVersicolor;
        } else if (irisVirginicaCounter > irisSetosaCounter && irisVirginicaCounter > irisVersicolorCounter) {
            resultClassName = irisVirginica;
        } else {
            // Do something if none of these is true, for example if some of the values are equal to one another
            // then use a index sorter to determine which of the values is higher in the array
        }

        return resultClassName;
    }

}
