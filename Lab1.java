import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Numbers num = new Numbers();

        int choice;
        do {
            displayMainMenu();

            try {
                System.out.print("> ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        num = new Numbers();
                        break;
                    case 2:
                        num = initArrayWithSize(scanner);
                        break;
                    case 3:
                        num.addValue(scanner);
                        break;
                    case 4:
                        System.out.println(num.toString());
                        break;
                    case 5:
                        displayStatistics(num);
                        break;
                    case 6:
                        num.addValues(scanner, true);
                        break;
                    case 7:
                        readValuesFromFile(num, scanner);
                        break;
                    case 8:
                        saveValuesToFile(num, scanner);
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                        scanner.nextLine();
                        choice = 0;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != 9);
    }

    private static Numbers initArrayWithSize(Scanner scanner) {
        int maxSize;
        do {
            System.out.print("Enter new size of array: ");
            maxSize = scanner.nextInt();
            if (maxSize <= 0) {
                System.out.println("Invalid input. Max size must be a positive integer. Try again.");
            }
        } while (maxSize <= 0);
        return new Numbers(maxSize);
    }

    private static void displayStatistics(Numbers num) {
        if (num.numItems == 0) {
            System.out.println("No values entered yet.");
        } else {
            float[] result = num.findMinMax();
            System.out.printf("Average: %.2f, Minimum value: %.2f, Maximum value: %.2f, " +
                    "Max mod Min: %.2f, Factorial of Max: %d%n",
                    num.calcAverage(), result[0], result[1], result[2], num.getfactorialMax());
        }
    }

    private static void readValuesFromFile(Numbers num, Scanner scanner) {
        System.out.print("Enter the file name to read values from: ");
        String readFile = scanner.next();
        num.readValuesFromFile(readFile);
    }

    private static void saveValuesToFile(Numbers num, Scanner scanner) {
        System.out.print("Enter the file name to save values: ");
        String saveFile = scanner.next();
        num.saveValuesToFile(saveFile);
    }

    private static void displayMainMenu() {
        System.out.println("Please select one of the following:");
        System.out.println("1: Initialize a default array");
        System.out.println("2: To specify the max size of the array");
        System.out.println("3: Add value to the array");
        System.out.println("4: Display values in the array");
        System.out.println("5: Display average, minimum value, maximum value, max mod min, factorialMax");
        System.out.println("6: Enter multiple values");
        System.out.println("7: Read values from file");
        System.out.println("8: Save values to file");
        System.out.println("9: To Exit");
    }
}
