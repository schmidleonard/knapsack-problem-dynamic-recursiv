package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to solve the knapsack problem using dynamic programming.
 * Implements Runnable to allow execution in a separate thread.
 */
public class AlgorithmDynamic implements Runnable {

    // Maximum weight capacity of the knapsack
    private final int W;
    // Array of item weights
    private final int[] wt;
    // Array of item values
    private final int[] val;

    /**
     * Constructor to initialize the knapsack's capacity and item arrays.
     * @param data Algorithm input data
     */
    public AlgorithmDynamic(InputData data) {
        this.W = data.capacity(); // Get the knapsack capacity from the first element of columnA
        this.wt = data.weights().stream().mapToInt(Integer::intValue).toArray(); // Convert columnB to an array of item weights
        this.val = data.values().stream().mapToInt(Integer::intValue).toArray(); // Convert columnC to an array of item values
    }

    /**
     * Method to solve the knapsack problem using dynamic programming.
     * @param W The maximum weight capacity of the knapsack.
     * @param wt Array of item weights.
     * @param val Array of item values.
     * @param n Number of items.
     * @return An array containing the maximum value at index 0,
     *         followed by the weights and values of the selected items.
     */
    public static int[] knapSack(int W, int[] wt, int[] val, int n) {
        int i, w;
        int[][] K = new int[n + 1][W + 1];

        // Build table K[][] in bottom-up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                // If either the item count or the weight is zero, no value can be obtained
                if (i == 0 || w == 0) {
                    K[i][w] = 0;
                }
                // If the item's weight is less than or equal to the current weight capacity
                else if (wt[i - 1] <= w) {
                    // Include the item and check if it's better to include it or not
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                }
                // If the item's weight is greater than the current weight capacity, it cannot be included
                else {
                    K[i][w] = K[i - 1][w];
                }
            }
        }

        // Lists to store the selected items' weights and values
        List<Integer> selectedWeights = new ArrayList<>();
        List<Integer> selectedValues = new ArrayList<>();

        // Traceback to find which items are included in the optimal solution
        w = W;
        for (i = n; i > 0 && w > 0; i--) {
            // If the item is included in the optimal solution
            if (K[i][w] != K[i - 1][w]) {
                // Add the item's weight and value to the respective lists
                selectedWeights.add(wt[i - 1]);
                selectedValues.add(val[i - 1]);
                // Reduce the weight capacity by the item's weight
                w = w - wt[i - 1];
            }
        }

        int[] selectedWeightsArray = selectedWeights.stream().mapToInt(Integer::intValue).toArray();
        int[] selectedValuesArray = selectedValues.stream().mapToInt(Integer::intValue).toArray();

        // Create the result array with the maximum value at index 0,
        // followed by the selected weights and values as pairs
        int[] result = new int[selectedWeightsArray.length * 2 + 1];
        result[0] = K[n][W]; // Store the maximum value
        for (int j = 0; j < selectedWeightsArray.length; j++) {
            result[1 + 2 * j] = selectedWeightsArray[j];
            result[2 + 2 * j] = selectedValuesArray[j];
        }

        return result;
    }

    /**
     * Runnable method to execute the dynamic knapsack algorithm.
     * Measures and prints the time taken for execution.
     */
    @Override
    public void run() {
        final long timeStart = System.currentTimeMillis();
        try {
            int n = val.length;
            int[] result = knapSack(W, wt, val, n);
            System.out.println("Maximum value (Dynamic): " + result[0]);
            for (int i = 1; i < result.length; i += 2) {
                System.out.println("Weight: " + result[i] + ", Value: " + result[i + 1]);
            }
        } catch (StackOverflowError e) {
            System.out.println("Too much data!");
        }

        final long timeEnd = System.currentTimeMillis();
        System.out.println("Time of Dynamic Algorithm: " + (timeEnd - timeStart) + " Millisek.");
    }
}
