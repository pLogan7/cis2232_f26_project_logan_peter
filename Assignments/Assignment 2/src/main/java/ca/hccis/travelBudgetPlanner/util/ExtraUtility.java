package ca.hccis.travelBudgetPlanner.util;

import ca.hccis.travelBudgetPlanner.util.CisUtility;

/**
 * Class for extra utility functionalities
 *
 * @author Peter Logan
 * @since 10/4/2026
 */
public class ExtraUtility {

    /**
     * Validates an integer input with min and max values
     *
     * @param min
     * @param max
     * @param message
     * @return validated integer number
     */
    public static int validateInteger(int min, int max, String message) {
        boolean validInput = true;
        int number = 0;
        do {
            try {
                number = CisUtility.getInputInt(message);

                if (number < min || number > max) {
                    validInput = false;
                    System.out.println("Number must be between " + min + " and " + max);
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
                validInput = false;
            }
        } while (!validInput);
        return number;
    }

    /**
     * Validates a double input with min and max values
     *
     * @param min
     * @param max
     * @param message
     * @return validated integer number
     */
    public static double validateDouble(double min, double max, String message) {
        boolean validInput = true;
        double number = 0;
        do {
            try {
                number = CisUtility.getInputDouble(message);

                if (number < min || number > max) {
                    validInput = false;
                    System.out.println("Number must be between " + min + " and " + max);
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
                validInput = false;
            }
        } while (!validInput);
        return number;
    }

}
