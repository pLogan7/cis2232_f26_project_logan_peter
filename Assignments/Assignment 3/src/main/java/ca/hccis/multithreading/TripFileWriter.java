package ca.hccis.multithreading;

import ca.hccis.travelBudgetPlanner.entity.Trip;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles writing Trip entries to a file. Thread-safe to allow both threads
 * to write entries without data corruption. The file accumulates all entries
 * from both the console and JOptionPane threads.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
public class TripFileWriter {

    private static final String FILE_PATH = "c:\\cis2232\\";
    private static final String FILE_PREFIX = "TravelBudgetTrips";

    /**
     * Writes a Trip entry to the report file in a thread-safe manner.
     * Creates the file if it does not already exist. Appends new entries.
     *
     * @param trip the Trip object to write to file
     * @param threadName identifies which thread created this entry
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    public static synchronized void writeTripToFile(Trip trip, String threadName) {
        String fileName = FILE_PREFIX + getCurrentTimestamp() + ".txt";
        String fullPath = FILE_PATH + fileName;

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullPath, true))) {
            writer.println("-----------------------------------------------------------");
            writer.println("Source: " + threadName);
            writer.println("Timestamp: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("-----------------------------------------------------------");
            writer.println("ID: " + trip.getId());
            writer.println("Destination: " + trip.getDestination());
            writer.println("Number of Travelers: " + trip.getNumberOfTravelers());
            writer.println("Number of Days: " + trip.getNumberOfDays());
            writer.println("Flight Cost: $" + String.format("%.2f", trip.getFlightCost()));
            writer.println("Hotel Cost Per Night: $" + String.format("%.2f", trip.getHotelCostPerNight()));
            writer.println("Food Cost Per Day: $" + String.format("%.2f", trip.getFoodCostPerDay()));
            writer.println("Transportation Cost: $" + String.format("%.2f", trip.getTransportationCost()));
            writer.println("Activities Cost: $" + String.format("%.2f", trip.getActivitiesCost()));
            writer.println("Travel Insurance: " + trip.isTravelInsurance());
            writer.println("Other Expenses: $" + String.format("%.2f", trip.getOtherExpenses()));
            writer.println("Total Cost: $" + String.format("%.2f", trip.getTotalCost()));
            writer.println("Cost Per Person: $" + String.format("%.2f", trip.getTotalCost() / trip.getNumberOfTravelers()));
            writer.println();
        } catch (IOException e) {
            IO.println("[ERROR] Could not write to file: " + e.getMessage());
        }
    }

    /**
     * Returns the current timestamp as a formatted string for use in filenames.
     *
     * @return formatted timestamp string (yyyyMMddHHmmss)
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
