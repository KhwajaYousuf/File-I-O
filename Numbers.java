import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;


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
     * Adds a specified number of float values to the array from user input using a Scanner.
     *
     * @param keyboard - Scanner object to use for input.
     * @param displayMessages Boolean flag indicating whether to display messages or not.
     */
    public void addValues(Scanner keyboard, boolean displayMessages) {
        if (displayMessages) {         // Display messages only if the flag is set to true
            System.out.print("How many values do you wish to add? ");
            int numValues = keyboard.nextInt();

            int remainingCapacity = numbers.length - numItems;           // Calculate the remaining capacity in the array

            if (remainingCapacity == 0) {                 // Check if there is no room in the array to add all values
                System.out.println("No room in array to add all values.");
                return;
            }

            for (int i = 0; i < numValues; i++) {
                if (remainingCapacity == 0) {
                    System.out.println("No room in array to add all values.");
                    break;
                }

                System.out.print("Enter value: ");
                try {
                    float inputValue = keyboard.nextFloat();                  // Reading float value from the user
                    numbers[numItems] = inputValue;
                    numItems++;                  // Add the value to the array and increment numItems
                    remainingCapacity--;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a float value.");                 // Handle invalid input (non-float) by displaying a message
                    keyboard.next(); // Consume the invalid input
                    i--; // Retry the current iteration
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Array is full. Cannot add more values.");                 // Handle the case where the array is full
                    break;
                }
            }
        }
    }

    
    /**
     * Reads float values from a file and adds them to the array.
     *
     * @param fileName The name of the file to read values from.
     */
    public void readValuesFromFile(String fileName) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));         // Create a BufferedReader to read from the file
            int itemCount = Integer.parseInt(reader.readLine().trim());        // Read the number of items to be read from the first line

            // Loop to read each float value from the file
            for (int i = 0; i < itemCount; i++) {
                float value = Float.parseFloat(reader.readLine().trim());             // Parse the float value from the next line and add it to the array
                numbers[numItems] = value;
                numItems++;
            }

            System.out.println("Values read from " + fileName);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading values from file: " + e.getMessage());
        
        }
    }

    
    /**
     * Saves the values in the array to a file.
     *
     * @param fileName The name of the file to save values to.
     */
    public void saveValuesToFile(String fileName) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(fileName);         // Create a FileWriter to write to the file

            writer.write(Integer.toString(numItems));         // Write the number of items as a string to the file
            writer.write(System.lineSeparator());        // Write a newline separator

            
            for (int i = 0; i < numItems; i++) {         // Loop to write each value from the 'numbers' array to the file
                writer.write(Float.toString(numbers[i]));             // Write the float value as a string to the file
                writer.write(System.lineSeparator());
            }

            System.out.println("Values saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving values to file: " + e.getMessage());
        } finally {
            try {
                if (writer != null) { // Closing FileWriter
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing FileWriter: " + e.getMessage());
            }
        }
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