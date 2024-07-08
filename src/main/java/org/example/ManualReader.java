package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualReader {
    public static InputData manualInput() {
        @SuppressWarnings("resource") // System.in is safely close at the end of execution
        Scanner userInput = new Scanner(System.in);
        List<Integer> weights = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        Integer capacity = -1;
        String input;


        System.out.println("Please enter the capacity of the backpack (a non-negative number).");
        while (capacity < 0) {
            try {
                capacity = Integer.parseInt(userInput.nextLine());
                if (capacity < 0) {
                    System.out.println("The capacity has to be a non-negative number, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid capacity.");
            }
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
            try {
                values.add(Integer.parseInt(userInput.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                weights.removeLast();
            }
        }
        return new InputData(capacity, weights, values);
    }
}
