package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to solve the knapsack problem using dynamic programming.
 * Implements Runnable to allow execution in a separate thread.
 */
public class AlgorithmDynamic extends Algorithm {

    /**
     * Constructor to initialize the knapsack's capacity and item arrays.
     * @param data Algorithm input data
     */
    public AlgorithmDynamic(InputData data) {
        super(data, "DYNAMIC");
    }

    /**
     * Method to solve the knapsack problem using dynamic programming.
     * @return An array containing the maximum value at index 0,
     *         followed by the weights and values of the selected items.
     */
    protected int[] runAlgorithm() {
        int i, w;
        int n = val.length;
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

}
