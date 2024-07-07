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

    public static void chooseInputType() {
        System.out.println("""
                Wie sollen die Daten eingelesen werden?\s
                - Beispieldatei: Drücke '1'\s
                - Eigene Datei einlesen: Drücke '2'\s
                - Manuelle Eingabe von Werten: Drücke '3'""");
        int chooseInput = userInput.nextInt();
        switch (chooseInput) {
            case 1:
                sampleFile();
                break;
            case 2:
                ownFilePath();
                break;
            case 3:
                manualInput();  // muss noch gebaut werden!
                break;
            default:
                System.out.println("Wähle eine Option mit '1', '2, oder '3'");
                chooseInputType();
        }

    }

    public static void ownFilePath() {
        System.out.println("Gebe den Dateipfad der Datei an:");
        System.out.println("Beispiel: C:\\\\Users\\\\Username\\\\Documents\\\\Filename.xlsx");

        // Read the file path from user input
        filePath = userInput.nextLine();
    }

    public static void sampleFile() {
        System.out.println("""
                Welche Dateigröße soll gewählt werden?\s
                klein: Drücke '1'\s
                groß: Drücke '2'""");
        int choose = userInput.nextInt();
        switch (choose) {
            case 1:
                Path filePathSmall = Paths.get("src", "main", "resources", "TestFileSmall.xlsx").toAbsolutePath();
                filePath = filePathSmall.toString();
                break;
            case 2:
                Path filePathBig = Paths.get("src", "main", "resources", "TestFileBig.xlsx").toAbsolutePath();
                filePath = filePathBig.toString();
                break;
            default:
                System.out.println("Wähle eine Datei mit '1' oder '2'");
                sampleFile();
        }

    }

    public static void manualInput() {
        // muss noch gebaut werden!
        System.out.println("Noch ned fertig!");
    }


    public static void main(String[] args) {

        chooseInputType();



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
