package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;

import function.Fitness;
import models.Individual;

/**
 * Created by Haider on 10/1/2014.
 */

/*
Genetic Algorithm Modification of the Iris Classification Data Set

 */


public class OldGeneticAlgorithm {

    double maxFitness = 0; // Initialized to zero first
    double fitnessThreshold;
    int noOfHypotheses;
    double replacementFraction;
    double mutationRate;
    double highestFitness;
    private ArrayList<Individual> population = new ArrayList<Individual>();
    private ArrayList<Fitness> fitnessArrayList = new ArrayList<Fitness>();
    private ArrayList<Double> fitnessList = new ArrayList<Double>();

    public OldGeneticAlgorithm(Fitness fitness, double fitnessThreshold, int noOfHypotheses,
                               double replacementFraction, double mutationRate) {
        this.fitnessThreshold = fitnessThreshold;
        this.noOfHypotheses = noOfHypotheses;
        this.replacementFraction = replacementFraction;
        this.mutationRate = mutationRate;
        initPopulation();
        fitnessArrayList = getFitness();

        /*
         Get [max Fitness(h)] and store in double
          */

        while (maxFitness < fitnessThreshold) {
            // Initialize new generation ArrayList
            ArrayList<Individual> sProbability = new ArrayList<Individual>();

            /*
            Run code to select, crossover, mutate, update, and evaluate.
             */

        }

        // get highestFitness.
    }


    private ArrayList<Individual> initPopulation() {
        Random random = new Random();
        for (int i = 0; i < population.size(); i++) {
            // Loop over list and generate population at random using Random object
        }
        return population;
    }

    private ArrayList<Fitness> getFitness() {

        // Initialize size of ArrayList...
        ArrayList<Fitness> arrayList = new ArrayList<Fitness>();
        // For each h in P compute fitness(h)
        for (int i = 0; i < arrayList.size(); i++) {
            fitnessList.add(i, arrayList.get(i).computeFitness(population.get(i)));
        }
        return arrayList;
    }
}
