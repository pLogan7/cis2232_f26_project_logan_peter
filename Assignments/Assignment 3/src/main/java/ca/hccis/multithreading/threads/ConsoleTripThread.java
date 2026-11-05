package ca.hccis.multithreading.threads;

import ca.hccis.multithreading.Controller;
import ca.hccis.multithreading.IO;
import ca.hccis.multithreading.TripFileWriter;
import ca.hccis.travelBudgetPlanner.bo.TripBO;
import ca.hccis.travelBudgetPlanner.entity.Trip;
import ca.hccis.util.CisUtility;

import javax.swing.*;

/**
 * Console-based thread for trip entry.
 * Extends Thread to demonstrate thread creation via inheritance.
 * Handles trip data input via System.out/System.in, calculates cost,
 * updates the shared accumulator, and writes to file.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
public class ConsoleTripThread extends Thread {

    private static final int MAX_TRIPS = 3;

    @Override
    public void run() {
        IO.println("[Console Thread] Starting...");
        IO.println("[Console Thread] Enter trip details below.");
        IO.println();

        for (int i = 0; i < MAX_TRIPS; i++) {
            try {
                Trip trip = collectTripData();
                processTrip(trip, "ConsoleThread");
            } catch (Exception e) {
                IO.println("[Console Thread] Error: " + e.getMessage());
            }
        }

        IO.println();
        IO.println("[Console Thread] Finished entering " + MAX_TRIPS + " trips.");
        IO.println("[Console Thread] Current total entries: " + Controller.totalTripEntries);
        IO.println("[Console Thread] Current total cost: $" + String.format("%.2f", Controller.totalCombinedCost));
    }

    /**
     * Collects trip data from the user via console input.
     *
     * @return a fully populated Trip object
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private Trip collectTripData() {
        Trip trip = new Trip();

        IO.print("[Console Thread] Destination: ");
        trip.setDestination(CisUtility.getInputString(""));

        IO.print("[Console Thread] Number of Travelers: ");
        trip.setNumberOfTravelers(CisUtility.getInputInt(""));

        IO.print("[Console Thread] Number of Days: ");
        trip.setNumberOfDays(CisUtility.getInputInt(""));

        IO.print("[Console Thread] Flight Cost: ");
        trip.setFlightCost(CisUtility.getInputDouble(""));

        IO.print("[Console Thread] Hotel Cost Per Night: ");
        trip.setHotelCostPerNight(CisUtility.getInputDouble(""));

        IO.print("[Console Thread] Food Cost Per Day: ");
        trip.setFoodCostPerDay(CisUtility.getInputDouble(""));

        IO.print("[Console Thread] Transportation Cost: ");
        trip.setTransportationCost(CisUtility.getInputDouble(""));

        IO.print("[Console Thread] Activities Cost: ");
        trip.setActivitiesCost(CisUtility.getInputDouble(""));

        IO.print("[Console Thread] Travel Insurance (y/n): ");
        trip.setTravelInsurance(CisUtility.getInputBoolean(""));

        IO.print("[Console Thread] Other Expenses: ");
        trip.setOtherExpenses(CisUtility.getInputDouble(""));

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

        // Display results
        IO.println();
        IO.println("-----------------------------------------------------------");
        IO.println("[Console Thread] Trip #" + trip.getId() + " - " + trip.getDestination());
        IO.println("[Console Thread] Total Cost: $" + String.format("%.2f", totalCost));
        IO.println("[Console Thread] Cost Per Person: $" +
                String.format("%.2f", totalCost / trip.getNumberOfTravelers()));
        IO.println("[Console Thread] Accumulator - Total Entries: " + Controller.totalTripEntries);
        IO.println("[Console Thread] Accumulator - Total Cost: $" +
                String.format("%.2f", Controller.totalCombinedCost));
        IO.println("-----------------------------------------------------------");

        // Write to file
        TripFileWriter.writeTripToFile(trip, threadName);
        IO.println("[Console Thread] Trip #" + trip.getId() + " written to file.");
    }
}
