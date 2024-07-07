package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main class to run the Excel data processing and algorithms.
 */
public class Main {

    static String filePath;
    static Scanner userInput = new Scanner(System.in);
    static int inputType;

    public static void chooseInputType() {
        System.out.println("""
                How should the data be read?\s
                - Example file: Press '1'\s
                - Read own file: Press '2'\s
                - Manual input of values: Press '3'""");
        int chooseInput = userInput.nextInt();
        inputType = chooseInput;
        userInput.nextLine(); // Consume the newline character left by nextInt()
        switch (chooseInput) {
            case 1:
                sampleFile();
                break;
            case 2:
                ownFilePath();
                break;
            case 3:
                //manualInput
                break;
            default:
                System.out.println("Choose an option with '1', '2', or '3'");
                chooseInputType();
        }
    }

    public static void ownFilePath() {
        System.out.println("Please provide the file path of the file:");
        System.out.println("Example: C:\\\\Users\\\\Username\\\\Documents\\\\Filename.xlsx");

        // Read the file path from user input
        filePath = userInput.nextLine();
    }

    public static void sampleFile() {
        System.out.println("""
                What file size should be chosen?\s
                Small: Press '1'\s
                Big: Press '2'""");
        int choose = userInput.nextInt();
        switch (choose) {
            case 1:
                Path filePathSmall = Paths.get("TestFileSmall.xlsx").toAbsolutePath();
                filePath = filePathSmall.toString();
                break;
            case 2:
                Path filePathBig = Paths.get( "TestFileBig.xlsx").toAbsolutePath();
                filePath = filePathBig.toString();
                break;
            default:
                System.out.println("Choose a file with '1' or '2'");
                sampleFile();
        }
    }


    public static void main(String[] args) {
        while (true) {
            chooseInputType();


            if (inputType == 1 || inputType == 2) {
                try {
                    // Create an instance of KnapsackReader to read the Excel file
                    KnapsackReader reader = new KnapsackReader(filePath);

                    // Process the first sheet (index 0) and extract data into an ExcelData object
                    InputData excelData = reader.processSheet(0);

                    // Create instances of the dynamic and recursion algorithms with the extracted data
                    AlgorithmDynamic dynamicAlgorithm = new AlgorithmDynamic(excelData.columnA(), excelData.columnB(), excelData.columnC());
                    AlgorithmRecursion recursionAlgorithm = new AlgorithmRecursion(excelData.columnA(), excelData.columnB(), excelData.columnC());

                    // Run the algorithm threads
                    dynamicAlgorithm.run();
                    recursionAlgorithm.run();


                } catch (Exception e) {

                    System.out.println("Something went wrong. Please try again.");
                    chooseInputType();
                }
            } else {

                InputData manualData = ManualReader.manualInput();
                // Create instances of the dynamic and recursion algorithms with the extracted data
                AlgorithmDynamic dynamicAlgorithm = new AlgorithmDynamic(manualData.columnA(), manualData.columnB(), manualData.columnC());
                AlgorithmRecursion recursionAlgorithm = new AlgorithmRecursion(manualData.columnA(), manualData.columnB(), manualData.columnC());

                // Run the algorithm threads
                dynamicAlgorithm.run();
                recursionAlgorithm.run();

            }

            System.out.println("""
                        New calculation? \s
                        Yes: '1' \s
                        No: '2'
                        """);
            int again = userInput.nextInt();
            userInput.nextLine(); // Consume the newline character left by nextInt()
            switch (again) {
                case 1: // Next loop
                    continue;
                case 2: // Exit program
                    System.exit(0);
            }
        }
    }
}