package lab_1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains the dynamically allocated array and its processing
 * Student Name: Khwaja Yousuf Mohiyuddeen
 * Student Number: 041-079-310
 * Course: CST8130 - Data Structures
 * CET-CS-Level 3
 * @author/Professor James Mwangi PhD.
 */

public class Numbers {
    /**
     * Stores Float values.
     */
    private Float[] numbers;

    /**
     * Store the number of items currently in the array.
     */
    public int numItems;

    /**
     * Default Constructor
     */
    public Numbers() {
        numbers = new Float[5];
        numItems = 0;
    }

    /**
     * Constructor that initializes the numbers array.
     *
     * @param size - Max size of the numbers array
     */
    public Numbers(int size) {
        numbers = new Float[size];
        numItems = 0;
    }

    /**
     * Adds a value in the array
     *
     * @param keyboard - Scanner object to use for input
     */
    public void addValue(Scanner keyboard) {    // Prompt user for input
        System.out.print("Enter a float value: ");

        while (true) {          // Read user input as a float with exception handling
            try {
                float inputValue = keyboard.nextFloat();
                numbers[numItems] = inputValue;
                numItems++;
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a float value: ");
                keyboard.next(); // Consume the invalid input
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array is full. Cannot add more values.");
                break;
            }
        }
    }

    /**
     * Calculates the average of all the values in the numbers array.
     *
     * @return float value that represents the average
     */
    public float calcAverage() {
        if (numItems == 0) {     // Check if the array is empty
            System.out.println("Cannot calculate average. Array is empty.");
            return 0.0f; 		 // Return 0.0 if the array is empty
        }

        float sum = 0.0f;		 // Initialize the sum of values to 0.0
        for (int i = 0; i < numItems; i++) {
            sum += numbers[i];
        }

        return sum / numItems;   // Calculate and return the average
    }

    /**
     * Returns an array containing the minimum and maximum values in the array
     * and the result of MaxValue modulo MinValue.
     *
     * @return float array containing minimum, maximum, and max mod min
     */
    public float[] findMinMax() {
        if (numItems == 0) {
            System.out.println("Array is empty. Cannot find min, max and max mod min.");
            return new float[]{};
        }

        float min = numbers[0]; // Initialize min with the first element
        float max = numbers[0]; // Initialize max with the first element

        for (int i = 1; i < numItems; i++) {     // Iterate through the array to find min and max
            if (numbers[i] < min) {
                min = numbers[i];		// Update min if the current element is smaller
            } else if (numbers[i] > max) {
                max = numbers[i];		// Update max if the current element is larger
            }	
        }

        float maxModMin = max % min; // Calculate and return result of MaxValue modulo MinValue
        return new float[]{min, max, maxModMin};
    }

    /**
     * Returns the factorial of MaxValue after dropping the decimal part.
     *
     * @return float value representing the factorial of MaxValue
     */
    public float getfactorialMax() {
        if (numItems == 0) {
            System.out.println("Array is empty. Cannot calculate factorial");
            return 0.0f;
        }

        float max = numbers[0];
        for (int i = 1; i < numItems; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        int maxIntValue = (int) max; 	// Drop the decimal part of the maximum value

        float factorial = 1.0f;
        for (int i = 1; i <= maxIntValue; i++) {     // Calculate the factorial of the integer part of the maximum value
            factorial *= i;
        }

        return factorial;   // Return the calculated factorial
    }

    
    /**
     * Returns a string representation of the Numbers object.
     * If the array is empty, the result will indicate that no numbers are present.
     * If the array has values, it will list each value on a new line.
     *
     * @return A string representation of the numbers in the array.
     */
    @Override
    public String toString() {
        if (numItems == 0) {
            return "Numbers are: \n";
        }

        String result = "Numbers are:\n";		// Initialize string result
        for (int i = 0; i < numItems - 1; i++) {           // Iterate through the array elements, excluding the last one
            result += numbers[i] + "\n";		// Concatenate the current element with a newline
        }
        result += numbers[numItems - 1]; // Last element without newline

        return result;
    }
}