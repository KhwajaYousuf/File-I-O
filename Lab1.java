package lab_1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Lab1 class contains the main method to test the Numbers class.
 * CET - CS Academic Level 3
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Khwaja Yousuf Mohiyuddeen
 * Student Number: 041-079-310
 * Course: CST8130 - Data Structures
 * @author/Professor: James Mwangi PhD. 
 * 
  */

public class Lab1 {

    /**
     * The main method to test the functionality of the Numbers class.
     *
     * @param args Command line arguments (not used in this application)
     */
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Numbers num = new Numbers(); // Create an instance of the Numbers class

        int choice;
        do {
            displayMainMenu(); // Calling displayMainMenu options

            try {
                System.out.print("> ");
                choice = scanner.nextInt(); // Read and store user input

                switch (choice) { // Start of switch statement
                    case 1: // Initialize a default array
                        num = new Numbers();
                        break;

                    case 2: // Specify the max size of the array
                        int maxSize;
                        while (true) {
                            System.out.print("Enter the max size: ");
                            maxSize = scanner.nextInt();
                            if (maxSize <= 0) {
                                System.out.println("Invalid input. Max size must be a positive integer. Try again.");
                            } else {
                                break;
                            }
                        }
                        num = new Numbers(maxSize);
                        System.out.println("Array initialized with max size: " + maxSize);
                        break;

                    case 3: // Add value to the array
                        num.addValue(scanner);
                        break;

                    case 4: // Display values in the array
                        System.out.println(num.toString());
                        break;

                    case 5:
                        // Display average, minimum, maximum, max mod min, factorialMax
                        if (num.numItems == 0) {
                            System.out.println("Average is: 0.0, Minimum value is 0.0, Maximum value is 0.0, Max mod Min is: _______ , Factorial of Max is: _______");
                        } else {
                            float[] result = num.findMinMax();
                            System.out.println("Average is: " + num.calcAverage() +
                                    ", Minimum value is: " + result[0] +
                                    ", Maximum value is: " + result[1] +
                                    ", Max mod Min is: " + result[2] +
                                    ", Factorial of Max is: " + num.getfactorialMax());
                        }
                        break;

                    case 6: // Exit option
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("...invalid input...try again...");
                        scanner.nextLine(); // Consume the invalid input
                        choice = 0; // Set choice to 0 to continue the loop
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("...invalid input...try again...");
                scanner.nextLine(); // Consume the invalid input
                choice = 0; // Set choice to 0 to continue the loop
            }	
        } while (choice != 6);
	}
	
	
	
    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("Please select one of the following:");
        System.out.println("1: Initialize a default array");
        System.out.println("2: To specify the max size of the array");
        System.out.println("3: Add value to the array");
        System.out.println("4: Display values in the array");
        System.out.println("5: Display average, minimum value, maximum value, max mod min, factorialMax");
        System.out.println("6: To Exit");
    }
}