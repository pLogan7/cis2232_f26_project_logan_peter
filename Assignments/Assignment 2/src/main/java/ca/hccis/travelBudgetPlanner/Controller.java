package ca.hccis.travelBudgetPlanner;

import ca.hccis.travelBudgetPlanner.bo.TripBO;
import ca.hccis.travelBudgetPlanner.entity.Trip;
import ca.hccis.travelBudgetPlanner.util.*;

/**
 * Controls the overall flow of the Travel Budget Planner application.
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
public class Controller {

    public static final int EXIT = 0;

    public static final String MENU =
            "1) Plan a New Trip" + System.lineSeparator() +
            "2) View Trip Details" + System.lineSeparator() +
            EXIT + ") Exit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error - Invalid Option";
    public static final String MESSAGE_EXIT = "Goodbye! Safe travels!";
    public static final String MESSAGE_SUCCESS = "Trip Cost Calculated Successfully";

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments (not used)
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public static void main(String[] args) {
        int menuOption;

        System.out.println("========================================");
        System.out.println("Welcome to the Travel Budget Planner Application");
        System.out.println("========================================");

        do {
            System.out.println(MENU);
            menuOption = CisUtility.getInputInt("Select an option");

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;
                case 1:
                    processOption1();
                    break;
                case 2:
                    processOption2();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (menuOption != EXIT);
    }

    /**
     * Processes menu option 1: Plan a new trip and calculate total cost.
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public static void processOption1() {
        Trip trip = new Trip();

        System.out.println("\n--- Plan a New Trip ---");
        trip.getInformation();

        // Calculate total cost using the business object
        double totalCost = TripBO.calculateTripCost(trip);
        trip.setTotalCost(totalCost);

        System.out.println("\n" + MESSAGE_SUCCESS);
        System.out.println("Destination: " + trip.getDestination());
        System.out.println("Travelers: " + trip.getNumberOfTravelers());
        System.out.println("Days: " + trip.getNumberOfDays());
        System.out.println("Total Estimated Cost: " + CisUtility.toCurrency(totalCost));
        System.out.println("Cost Per Person: " + CisUtility.toCurrency(totalCost / trip.getNumberOfTravelers()));
    }

    /**
     * Processes menu option 2: View trip details for a pre-entered trip.
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public static void processOption2() {
        System.out.println("\n--- View Trip Details ---");
        System.out.println("Feature coming soon.");
    }
}
