package ca.hccis.util;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Has some useful methods to be used in our programs.
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
public class CisUtility {

    private static Scanner input = new Scanner(System.in);

    /**
     * Return the default currency String value of the double passed in as a parameter.
     *
     * @param inputDouble double to be formatted
     * @return String in default currency format
     *
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static String toCurrency(double inputDouble) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(inputDouble);
    }

    /**
     * Get input from the user using the console.
     *
     * @param prompt Prompt for the user
     * @return String entered by the user
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static String getInputString(String prompt) {
        String output = input.nextLine();
        return output;
    }

    /**
     * Get input from the user using the console.
     *
     * @param prompt     Prompt for the user
     * @param minLength  Minimum length of string
     * @param maxLength  Maximum length of string
     * @return String entered by the user
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static String getInputString(String prompt, int minLength, int maxLength) {
        String output = input.nextLine();

        while (output.length() < minLength || output.length() > maxLength) {
            output = input.nextLine();
        }

        return output;
    }

    /**
     * Get input from the user using the console.
     *
     * @param prompt Prompt for the user
     * @return The double entered by the user
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static double getInputDouble(String prompt) {
        String inputString = getInputString(prompt);
        double output = Double.parseDouble(inputString);
        return output;
    }

    /**
     * Get input from the user using the console.
     *
     * @param prompt Prompt for the user
     * @return The int entered by the user
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static int getInputInt(String prompt) {
        String inputString = getInputString(prompt);
        int output = Integer.parseInt(inputString);
        return output;
    }

    /**
     * Get input boolean from the user using the console.
     *
     * @param prompt Prompt for the user
     * @return boolean as specified by user input
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static boolean getInputBoolean(String prompt) {
        String inputString = getInputString(prompt + " (y/n)");
        return inputString.equalsIgnoreCase("y");
    }

    /**
     * Provide today's date in the specified format.
     *
     * @param format Date format desired
     * @return Today's date in specified format
     * @since 2026-10-04
     * @author Peter Logan
     */
    public static String getTodayString(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
