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


        System.out.println("Please enter the capacity of the backpack.");
        //input = userInput.nextLine();
        try {
            int capa = userInput.nextInt();
            capacity.add(capa);
            userInput.nextLine(); // Consume the newline character left by nextInt()
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        while (true) {
            System.out.print("Enter the weight (or 'exit' when all values are entered): ");
            input = userInput.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                int weight = Integer.parseInt(input);
                weights.add(weight);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            System.out.print("Enter the value: ");
            input = userInput.nextLine();
            try {
                int value = Integer.parseInt(input);
                values.add(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                weights.removeLast();
            }
        }
        return new InputData(capacity, weights, values);
    }
}
