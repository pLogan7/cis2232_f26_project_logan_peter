package ca.hccis.multithreading.threads;

import ca.hccis.multithreading.Controller;
import ca.hccis.multithreading.IO;
import ca.hccis.multithreading.TripFileWriter;
import ca.hccis.travelBudgetPlanner.bo.TripBO;
import ca.hccis.travelBudgetPlanner.entity.Trip;

import javax.swing.*;

/**
 * JOptionPane-based thread for trip entry.
 * Implements Runnable to demonstrate thread creation via Runnable interface.
 * Handles trip data input via JOptionPane dialogs, calculates cost,
 * updates the shared accumulator, and writes to file.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
public class DialogTripThread implements Runnable {

    private static final int MAX_TRIPS = 3;

    @Override
    public void run() {
        IO.println("[Dialog Thread] Starting...");
        JOptionPane.showMessageDialog(null,
                "Dialog Thread: Enter trip details below.\n" +
                "You will enter " + MAX_TRIPS + " trips.",
                "Travel Budget Planner - Dialog Thread",
                JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i < MAX_TRIPS; i++) {
            try {
                Trip trip = collectTripData();
                processTrip(trip, "DialogThread");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                IO.println("[Dialog Thread] Error: " + e.getMessage());
            }
        }

        IO.println();
        IO.println("[Dialog Thread] Finished entering " + MAX_TRIPS + " trips.");
        IO.println("[Dialog Thread] Current total entries: " + Controller.totalTripEntries);
        IO.println("[Dialog Thread] Current total cost: $" + String.format("%.2f", Controller.totalCombinedCost));

        JOptionPane.showMessageDialog(null,
                "Dialog Thread Complete!\n\n" +
                "Total Entries: " + Controller.totalTripEntries + "\n" +
                "Total Combined Cost: $" + String.format("%.2f", Controller.totalCombinedCost),
                "Travel Budget Planner - Summary",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Collects trip data from the user via JOptionPane dialogs.
     *
     * @return a fully populated Trip object
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private Trip collectTripData() {
        Trip trip = new Trip();

        trip.setDestination(getInputString("Destination:"));
        trip.setNumberOfTravelers(getInputInt("Number of Travelers:"));
        trip.setNumberOfDays(getInputInt("Number of Days:"));
        trip.setFlightCost(getInputDouble("Flight Cost:"));
        trip.setHotelCostPerNight(getInputDouble("Hotel Cost Per Night:"));
        trip.setFoodCostPerDay(getInputDouble("Food Cost Per Day:"));
        trip.setTransportationCost(getInputDouble("Transportation Cost:"));
        trip.setActivitiesCost(getInputDouble("Activities Cost:"));
        trip.setTravelInsurance(getInputBoolean("Travel Insurance (Yes/No):"));
        trip.setOtherExpenses(getInputDouble("Other Expenses:"));

        return trip;
    }

    /**
     * Processes a trip: calculates cost, updates accumulator, displays results,
     * and writes to file.
     *
     * @param trip the Trip object to process
     * @param threadName identifier for this thread
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private void processTrip(Trip trip, String threadName) {
        // Calculate total cost using TripBO
        double totalCost = TripBO.calculateTripCost(trip);
        trip.setTotalCost(totalCost);

        // Assign ID and update shared accumulator (synchronized access)
        synchronized (Controller.class) {
            Controller.totalTripEntries++;
            trip.setId(Controller.totalTripEntries);
            Controller.totalCombinedCost += totalCost;
        }

        // Display results in dialog
        String resultMessage = String.format(
                "Trip #%d - %s%n" +
                "Total Cost: $%.2f%n" +
                "Cost Per Person: $%.2f%n" +
                "%n" +
                "Accumulator - Total Entries: %d%n" +
                "Accumulator - Total Cost: $%.2f",
                trip.getId(), trip.getDestination(),
                totalCost, totalCost / trip.getNumberOfTravelers(),
                Controller.totalTripEntries, Controller.totalCombinedCost
        );

        JOptionPane.showMessageDialog(null, resultMessage,
                "Trip Result - Dialog Thread",
                JOptionPane.INFORMATION_MESSAGE);

        IO.println();
        IO.println("-----------------------------------------------------------");
        IO.println("[Dialog Thread] Trip #" + trip.getId() + " - " + trip.getDestination());
        IO.println("[Dialog Thread] Total Cost: $" + String.format("%.2f", totalCost));
        IO.println("[Dialog Thread] Cost Per Person: $" +
                String.format("%.2f", totalCost / trip.getNumberOfTravelers()));
        IO.println("[Dialog Thread] Accumulator - Total Entries: " + Controller.totalTripEntries);
        IO.println("[Dialog Thread] Accumulator - Total Cost: $" +
                String.format("%.2f", Controller.totalCombinedCost));
        IO.println("-----------------------------------------------------------");

        // Write to file
        TripFileWriter.writeTripToFile(trip, threadName);
        IO.println("[Dialog Thread] Trip #" + trip.getId() + " written to file.");
    }

    // =========================================================================
    // Helper methods for JOptionPane input
    // =========================================================================

    /**
     * Gets a string input from the user via JOptionPane.
     *
     * @param prompt the prompt message
     * @return the string entered by the user
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private String getInputString(String prompt) {
        String input = JOptionPane.showInputDialog(null, prompt,
                "Travel Budget Planner", JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            return "";
        }
        return input;
    }

    /**
     * Gets an integer input from the user via JOptionPane.
     *
     * @param prompt the prompt message
     * @return the integer entered by the user
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private int getInputInt(String prompt) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, prompt,
                    "Travel Budget Planner", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return 0;
            }
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a valid number.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Gets a double input from the user via JOptionPane.
     *
     * @param prompt the prompt message
     * @return the double entered by the user
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private double getInputDouble(String prompt) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, prompt,
                    "Travel Budget Planner", JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return 0.0;
            }
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a valid number.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Gets a boolean input from the user via JOptionPane.
     *
     * @param prompt the prompt message
     * @return the boolean entered by the user
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private boolean getInputBoolean(String prompt) {
        int result = JOptionPane.showConfirmDialog(null, prompt,
                "Travel Budget Planner", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
}
