package org.example;

abstract class Algorithm implements Runnable {

    // Maximum weight capacity of the knapsack
    protected int W;
    // Array of item weights
    protected int[] wt;
    // Array of item values
    protected int[] val;
    // lable of the actual algorithm
    protected String label;

    /**
     * Constructor to initialize the knapsack's capacity and item arrays.
     * @param data Algorithm input data
     */
    public Algorithm(InputData data, String label) {
        this.W = data.capacity(); // Get the knapsack capacity from the first element of columnA
        this.wt = data.weights().stream().mapToInt(Integer::intValue).toArray(); // Convert columnB to an array of item weights
        this.val = data.values().stream().mapToInt(Integer::intValue).toArray(); // Convert columnC to an array of item values
        this.label = label;
    }

    /**
     * Prints a message with the label prefix
     * @param message a message to print
     */
    protected void report(String message) {
        System.out.println(this.label + ": " + message);
    }

    abstract protected int[] runAlgorithm();

    /**
     * Runnable method to execute the knapsack algorithm.
     * Measures and prints the time taken for execution.
     */
    @Override
    public void run() {
        final long timeStart = System.currentTimeMillis();
        try {
            int[] result = runAlgorithm();
            // TODO: this should be synchronized - when more algorithms/threads would be writing the report at the some time (e.g. for small backpacks of around 6 items), it might be interleaved
            this.report("Maximum value: " + result[0]);
            for (int i = 1; i < result.length; i += 2) {
                this.report("Weight: " + result[i] + ", Value: " + result[i + 1]);
            }
        } catch (StackOverflowError e) {
            this.report("Too much data!");
        }

        final long timeEnd = System.currentTimeMillis();
        this.report("Time of Algorithm: " + (timeEnd - timeStart) + " Millisek.");
    }

}
