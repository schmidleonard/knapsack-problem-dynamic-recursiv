package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to solve the knapsack problem using recursion.
 * Implements Runnable to allow execution in a separate thread.
 */
public class AlgorithmRecursion implements Runnable {

    // Maximum weight capacity of the knapsack
    private final int W;
    // Array of item weights
    private final int[] wt;
    // Array of item values
    private final int[] val;

    /**
     * Constructor to initialize the knapsack's capacity and item arrays.
     * @param columnA List containing the knapsack capacity as its first element.
     * @param columnB List of item weights.
     * @param columnC List of item values.
     */
    public AlgorithmRecursion(List<Integer> columnA, List<Integer> columnB, List<Integer> columnC) {
        this.W = columnA.getFirst(); // Get the knapsack capacity from the first element of columnA
        this.wt = columnB.stream().mapToInt(Integer::intValue).toArray(); // Convert columnB to an array of item weights
        this.val = columnC.stream().mapToInt(Integer::intValue).toArray(); // Convert columnC to an array of item values
    }

    /**
     * Method to solve the knapsack problem using recursion.
     * @param W The maximum weight capacity of the knapsack.
     * @param wt Array of item weights.
     * @param val Array of item values.
     * @param n Number of items.
     * @return An array containing the maximum value at index 0,
     *         followed by the weights and values of the selected items.
     */
    public static int[] knapSack(int W, int[] wt, int[] val, int n) {
        // Lists to store the selected items' weights and values
        List<Integer> selectedWeights = new ArrayList<>();
        List<Integer> selectedValues = new ArrayList<>();

        // Call the recursive helper method
        int maxValue = knapSackRec(W, wt, val, n, selectedWeights, selectedValues);

        // Convert lists to arrays
        int[] selectedWeightsArray = selectedWeights.stream().mapToInt(Integer::intValue).toArray();
        int[] selectedValuesArray = selectedValues.stream().mapToInt(Integer::intValue).toArray();

        // Create the result array with the maximum value at index 0,
        // followed by the selected weights and values
        int[] result = new int[selectedWeightsArray.length + selectedValuesArray.length + 1];
        result[0] = maxValue; // Store the maximum value
        for (int i = 0; i < selectedWeightsArray.length; i++) {
            result[1 + 2 * i] = selectedWeightsArray[i];
            result[2 + 2 * i] = selectedValuesArray[i];
        }

        return result;
    }

    /**
     * Recursive helper method to solve the knapsack problem.
     * @param W The maximum weight capacity of the knapsack.
     * @param wt Array of item weights.
     * @param val Array of item values.
     * @param n Number of items.
     * @param selectedWeights List to store the weights of the selected items.
     * @param selectedValues List to store the values of the selected items.
     * @return The maximum value that can be obtained.
     */
    private static int knapSackRec(int W, int[] wt, int[] val, int n, List<Integer> selectedWeights, List<Integer> selectedValues) {
        // Base cases
        if (n == 0 || W == 0) {
            return 0;
        }

        // If the weight of the nth item is more than the capacity W, it is not included
        if (wt[n - 1] > W) {
            return knapSackRec(W, wt, val, n - 1, selectedWeights, selectedValues);
        } else {
            // Otherwise, calculate the maximum value by including or excluding the item
            List<Integer> includeWeights = new ArrayList<>(selectedWeights);
            List<Integer> includeValues = new ArrayList<>(selectedValues);
            int includeValue = val[n - 1] + knapSackRec(W - wt[n - 1], wt, val, n - 1, includeWeights, includeValues);
            includeWeights.add(wt[n - 1]);
            includeValues.add(val[n - 1]);

            List<Integer> excludeWeights = new ArrayList<>(selectedWeights);
            List<Integer> excludeValues = new ArrayList<>(selectedValues);
            int excludeValue = knapSackRec(W, wt, val, n - 1, excludeWeights, excludeValues);

            // Choose the option that gives the maximum value
            selectedWeights.clear();
            if (includeValue > excludeValue) {
                selectedWeights.addAll(includeWeights);
                selectedValues.clear();
                selectedValues.addAll(includeValues);
                return includeValue;
            } else {
                selectedWeights.addAll(excludeWeights);
                selectedValues.clear();
                selectedValues.addAll(excludeValues);
                return excludeValue;
            }
        }
    }

    /**
     * Runnable method to execute the recursive knapsack algorithm.
     * Measures and prints the time taken for execution.
     */
    @Override
    public void run() {

        final long timeStart = System.currentTimeMillis();
        try {
            int n = val.length;
            int[] result = knapSack(W, wt, val, n);
            System.out.println("Maximum value (Recursion): " + result[0]);
            for (int i = 1; i < result.length; i += 2) {
                System.out.println("Weight: " + result[i] + ", Value: " + result[i + 1]);
            }
        } catch (StackOverflowError e) {
            System.out.println("Too much data for recursion!");
        }

        final long timeEnd = System.currentTimeMillis();
        System.out.println("Time of Recursion Algorithm: " + (timeEnd - timeStart) + " Millisec.");
    }
}
