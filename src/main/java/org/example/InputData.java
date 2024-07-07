package org.example;

import java.util.List;

/**
 * Record class to encapsulate data extracted from an Excel sheet or from manual input.
 *
 * @param capacity A capacity of the knapsack
 * @param weights List of weights
 * @param values List of values
 */
public record InputData(Integer capacity, List<Integer> weights, List<Integer> values) {

    /**
     * Overridden toString method to provide a string representation of the InputData object.
     * @return A string representation of the InputData object.
     */
    @Override
    public String toString() {
        return "InputData{" +
                "Capacity=" + capacity +
                ", weights=" + weights +
                ", values=" + values +
                '}';
    }
}