package haiderllc.com.irisclassification.network;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;
import haiderllc.com.irisclassification.models.Iris;

/**
 * Created by Haider on 9/24/2014.
 */

/*
Class Description: This class takes in a .data file and splits the values with .split(",")
    method to separate the string values into the numbers[] array, the values for the
    sepal length, width; petal length, width are then saved into the iris object
 */

public class MachineLearningInstance {

    /*
         * Setup Arraylist of Iris Items which will be the size of the .data file
         */
    private ArrayList<Iris> irisData = new ArrayList<Iris>();
    private ArrayList<Iris> trainingArray = new ArrayList<Iris>();
    private Context context;


    /*
     * These are values for the NeuralNetwork Constructor
     */
    private final String comma = ",";
    private final String irisSetosa = "Iris-setosa";
    private final String irisVersicolor = "Iris-versicolor";
    private final String irisVirginica = "Iris-virginica";

    public MachineLearningInstance(File f, Context context) {

        this.context = context;

        try {

            int noOfRowsInData = 0;

            LineNumberReader lnr = new LineNumberReader(new FileReader(f));
            try {
                lnr.skip(Long.MAX_VALUE);
                noOfRowsInData = lnr.getLineNumber();
                //System.out.println(noOfRowsInData);
                lnr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            irisData = new ArrayList<Iris>();

            // While there is another line in inFile.
            Scanner inFile = new Scanner(f);

            for (int i = 0; i < noOfRowsInData - 1; i++) {

                if (inFile.hasNextLine()) {
                    // Store line into String
                    String line = inFile.nextLine();

                    // Partition values into separate elements in array
                    String[] numbers = line.split(comma);

                    // Grab values from that line and store it into a Iris ArrayList Item
                    irisData.add(i, new Iris(i, numbers[0], numbers[1], numbers[2], numbers[3], numbers[4]));

                }
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (irisData == null) {
            Toast.makeText(context, "IRIS DATA IS NULL!", Toast.LENGTH_LONG);
        }

        for (int i = 0; i < irisData.size(); i++) {
            Toast.makeText(context, irisData.get(i).getId(), Toast.LENGTH_SHORT).show();
            Toast.makeText(context, irisData.get(i).getIrisClassName(), Toast.LENGTH_SHORT).show();

        } // Used to check values

        //sortData();

    }

    /*
    Initialize method to grab the 2-D array of the Iris items
     */
    public ArrayList<Iris> getTestData() {
        return irisData;
    }

    public ArrayList<Iris> getTrainingData() {
        return trainingArray;
    }

    public ArrayList<Iris> sortData() {

        trainingArray.addAll(irisData.subList(0, 100));

        return trainingArray;
    }


//    public static void main(String[] args) {
//
//        // Setup MachineLearning Instance as an example to test out what is going on in the code.
//        MachineLearningInstance machineLearningInstance = new MachineLearningInstance(
//                new File("C:\\iris\\iris.data"));
//
//
//    }

}
