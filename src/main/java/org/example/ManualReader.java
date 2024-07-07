package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualReader {
    public static InputData manualInput() {
        Scanner userInput = new Scanner(System.in);
        List<Integer> weights = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        List<Integer> capacity = new ArrayList<>();
        String input;


        System.out.println("Geben sie die Kapazität des Rucksacks ein.");
        //input = userInput.nextLine();
        try {
            //int capa = Integer.parseInt(input);
            int capa = userInput.nextInt();
            capacity.add(capa);
            userInput.nextLine(); // Consume the newline character left by nextInt()
        } catch (NumberFormatException e) {
            System.out.println("Bitte eine gültige Zahl eingeben.");
        }
        while (true) {
            System.out.print("Geben Sie das Gewicht ein (oder 'exit' zum wenn alle Werte eingetragen sind): ");
            input = userInput.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int weight = Integer.parseInt(input);
                weights.add(weight);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben.");
                continue;
            }

            System.out.print("Geben Sie den Wert ein: ");
            input = userInput.nextLine();
            try {
                int value = Integer.parseInt(input);
                values.add(value);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben.");
                weights.removeLast();
            }
        }
        return new InputData(capacity, weights, values);
    }
}
