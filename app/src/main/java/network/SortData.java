package network;

import java.util.ArrayList;
import java.util.Random;

import models.Iris;

/**
 * Created by Haider on 9/25/2014.
 */
public class SortData {


    private Random randomGenerator;
    private ArrayList<Iris> irisItems;
    private ArrayList<Iris> partitionArray;

    // 1.) Take in ArrayList of Iris
    // 2.) Actual Iris Object
    // 3.) After being sorted put top k items (might be third parameter to sorting method)
    //          Sorting from least to most and then only keeping bottom k and comparing the query point
    // 4.) After sorting, you go through top k and find which of the categories gets the most votes
    //      do getClassName for each one of top k and have a voting thing,
    // 5.) if you have a tie go back and pick another one
    // 6.) Work out on a piece of paper, or print out

    public SortData(ArrayList<Iris> irisItems) {
        this.irisItems = irisItems;
        randomGenerator = new Random();

        int i = 0;
        while (irisItems.size() >= 100) {
            partitionArray.add(i, irisItems.remove(randomGenerator.nextInt(irisItems.size())));
            i++;
        }
    }

    public ArrayList<Iris> getTestData() {
        return irisItems;
    }

    public ArrayList<Iris> getTrainingData() {
        return partitionArray;
    }
}
