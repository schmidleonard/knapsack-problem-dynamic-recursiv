## Knapsack Problem Solver


This repository contains a Java program to solve the Knapsack Problem using dynamic and recursive algorithms. It demonstrates the efficiency of the dynamic approach compared to the recursive solution.
Overview

The Knapsack Problem is a classic optimization problem where the goal is to pack a set of items with given weights and values into a knapsack with a limited capacity to maximize the total value.
If you want to know more about the knapsack problem, click here: https://de.wikipedia.org/wiki/Rucksackproblem

This program offers the following options for data input:

- Using a sample file (TestFileSmall.xlsx or TestFileBig.xlsx).
- Using a custom Excel file.
- Manual input of values.




## Usage

### Prerequisites

- Java JDK 22 must be installed.
- Download the JDK 22 here: https://www.oracle.com/de/java/technologies/downloads/#java22

Running the Program

- Unzip the knapsack-problem-solver.zip file.
- Open a terminal or console and navigate to the directory where the .jar file is located.
- Run the program with the command:

      java -jar filename.jar

### User Instructions

After starting the program, you will be asked how to input the data:

    Sample File:
        Press '1'.
        Choose between a small (TestFileSmall.xlsx) and a large (TestFileBig.xlsx) file.
        If the sample files are to be used, they must be placed in the same directory as the .jar file!
    Custom File:
        Press '2'.
        Enter the path to your Excel file.
    Manual Input:
        Press '3'.
        Follow the instructions to enter capacity, weights, and values.

The program runs the algorithms in separate threads and displays the results, including the time taken for both approaches.

### Sample XLSX File

A blank sample file Blank.xlsx is included in the main directory. You can use this file to enter your own values and test the program.
It's important that when entering the path of the input file, "\" is specified as "\\"




## Algorithms

### Dynamic Algorithm

The dynamic algorithm uses dynamic programming to efficiently solve the knapsack problem by building a table that stores the best solutions for smaller subproblems and using these solutions to solve the overall problem.
Steps of the Dynamic Algorithm:

    Initialization:
        A two-dimensional table K is created where K[i][w] represents the maximum value that can be achieved with the first i items and a weight limit of w.
        The table has dimensions (n+1) x (W+1), where n is the number of items and W is the maximum capacity of the knapsack.

    Table Construction:
        Iterate over all items (i from 0 to n) and all possible weight capacities (w from 0 to W).
        If i == 0 or w == 0, set K[i][w] to 0 since there are no items or capacity.
        If the weight of the current item wt[i-1] is less than or equal to the current capacity w, there are two options:
            Include the item: The value is val[i-1] plus the maximum value of the remaining capacity K[i-1][w-wt[i-1]].
            Exclude the item: The value remains K[i-1][w].
            Choose the maximum of these two options.
        If the item's weight is greater than the current capacity, exclude the item and carry over the value K[i-1][w].

    Backtracking:
        The maximum value is found at K[n][W].
        The optimal items can be identified by backtracking through the table K to see which items contribute to the maximum value.

### Recursive Algorithm

The recursive algorithm solves the knapsack problem by repeatedly breaking it down into smaller subproblems. This approach is intuitive but can be inefficient due to the many recursive calls, especially for larger problem sizes.
Steps of the Recursive Algorithm:

    Base Cases:
        If no items are left or the knapsack's capacity is 0, the maximum value is 0.

    Recursive Decisions:
        For each item, there are two options:
            Include the item if its weight is less than or equal to the current capacity of the knapsack.
            Exclude the item.
        The function is called recursively to compute the maximum value for both options:
            If the item is included, add the item's value to the maximum value of the remaining capacity.
            If the item is excluded, the value remains unchanged.
        Return the maximum of these two options.
## Classes

### Main.java

This class serves as the entry point of the program.
It initializes the program, processes user inputs, and controls the execution flow of the Knapsack algorithms.

### AlgorithmDynamic.java

Implementation of the dynamic programming approach for the Knapsack problem.
This class uses bottom-up dynamic programming to efficiently compute the optimal solution for the Knapsack problem. It accesses input data and updates results step by step.

### AlgorithmRecursion.java

Implementation of the recursive approach for the Knapsack problem.
This class recursively solves the Knapsack problem, demonstrating the inefficiency of this approach, especially with larger datasets.

### AbstractExcelReader.java

Abstract class for reading data from Excel files.
Defines methods and attributes that concrete reader classes implement to parse specific data structures from Excel files.

### KnapsackReader.java

Concrete implementation of AbstractExcelReader for reading Knapsack data from Excel.
This class implements specific logic for parsing and validating Excel files containing data necessary for the Knapsack problem.

### InputData.java

Data container for Knapsack problem input values.
Stores the backpack capacity, weights, and values of items. This data structure is used by the algorithms to compute the optimal solution.

### ManualReader.java

Class for manual input of Knapsack data.
Processes user inputs via the console to capture the backpack capacity, weights, and values of items.



##  Program Flow

1. Starting Point: The Main.java class initializes the program and expects the user to choose an input method.

2. Data Input: Based on user selection, either KnapsackReader.java or ManualReader.java is used to capture input data and store it in InputData.java.

3. Algorithm Execution: The Main.java class initializes and starts AlgorithmDynamic.java and AlgorithmRecursion.java in separate threads.

3. Algorithmic Calculation: Both algorithms access the data stored in InputData.java independently to compute the optimal solution for the Knapsack problem.

4. Result Output: The results of the algorithms (maximum value and computation time) are displayed after completing the calculations.
