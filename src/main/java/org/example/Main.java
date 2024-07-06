package org.example;

import java.util.Scanner;

/**
 * Main class to run the Excel data processing and algorithms.
 */
public class Main {

    public static void main(String[] args) {

        // Create a Scanner object for reading user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the file path of the Excel file
        System.out.println("Gebe den Dateipfad der Datei an:");
        System.out.println("Beispiel: C:\\\\Users\\\\Username\\\\Documents\\\\Filename.xlsx");

        // Read the file path from user input
        String filePath = scanner.nextLine();

        try {
            // Create an instance of KnapsackReader to read the Excel file
            KnapsackReader reader = new KnapsackReader(filePath);

            // Process the first sheet (index 0) and extract data into an ExcelData object
            ExcelData excelData = reader.processSheet(0);

            // Create instances of the dynamic and recursion algorithms with the extracted data
            AlgorithmDynamic dynamicAlgorithm = new AlgorithmDynamic(excelData.columnA(), excelData.columnB(), excelData.columnC());
            AlgorithmRecursion recursionAlgorithm = new AlgorithmRecursion(excelData.columnA(), excelData.columnB(), excelData.columnC());

            // Run the algorithm threads
            dynamicAlgorithm.run();
            recursionAlgorithm.run();

        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
