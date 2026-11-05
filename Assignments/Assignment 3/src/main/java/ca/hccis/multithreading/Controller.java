package ca.hccis.multithreading;

import ca.hccis.multithreading.threads.ConsoleTripThread;
import ca.hccis.multithreading.threads.DialogTripThread;

import javax.swing.*;

/**
 * Main controller for the multi-threaded Travel Budget Planner application.
 * Launches two separate threads:
 * - ConsoleTripThread: handles trip entry via the console
 * - DialogTripThread: handles trip entry via JOptionPane dialogs
 * Both threads share the same accumulator and write to the same file.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
public class Controller {

    /**
     * Static accumulator shared by both threads.
     * Tracks the total number of trip entries created across both threads.
     */
    public static int totalTripEntries = 0;

    /**
     * Static accumulator shared by both threads.
     * Tracks the combined total cost of all trips from both threads.
     */
    public static double totalCombinedCost = 0.00;

    /**
     * Main entry point. Starts both threads and waits for them to complete.
     *
     * @param args Command line arguments (not used)
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    public static void main(String[] args) {
        IO.println("========================================");
        IO.println("   Travel Budget Planner - Multi-Thread");
        IO.println("========================================");
        IO.println();

        long start = System.currentTimeMillis();
        IO.println("Application start time (ms): " + start);

        // Thread 1: Console-based trip entry (extends Thread)
        ConsoleTripThread consoleThread = new ConsoleTripThread();
        consoleThread.start();

        // Thread 2: JOptionPane-based trip entry (implements Runnable)
        Thread dialogThread = new Thread(new DialogTripThread());
        dialogThread.start();

        // Wait for both threads to complete
        try {
            consoleThread.join();
            dialogThread.join();
        } catch (InterruptedException e) {
            IO.println("[ERROR] Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        long end = System.currentTimeMillis();
        IO.println();
        IO.println("Application end time (ms): " + end);
        IO.println("Duration: " + (end - start) + " ms");
        IO.println();
        IO.println("========================================");
        IO.println("   Final Accumulator Summary");
        IO.println("========================================");
        IO.println("Total Trip Entries (both threads): " + totalTripEntries);
        IO.println("Total Combined Cost: $" + String.format("%.2f", totalCombinedCost));
        IO.println("========================================");
    }
}
