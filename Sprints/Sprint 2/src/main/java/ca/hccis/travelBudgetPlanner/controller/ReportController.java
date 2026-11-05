package ca.hccis.travelBudgetPlanner.controller;

import ca.hccis.travelBudgetPlanner.dao.TripDAO;
import ca.hccis.travelBudgetPlanner.dao.TripReportDAO;
import ca.hccis.travelBudgetPlanner.model.TripReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for the Trip Summary Report functionality.
 * Handles the report input form, processes the report by invoking the DAO,
 * displays results, and writes the report to a file.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
@Controller
public class ReportController {

    private static final String FILE_PATH = "c:\\cis2232\\";
    private static final String FILE_PREFIX = "TripSummaryReport";

    @Autowired
    private TripReportDAO tripReportDAO;

    @Autowired
    private TripDAO tripDAO;

    /**
     * Shows the "View All Trips" report page displaying every trip in the database.
     *
     * @param model Spring Model to pass data to the view
     * @return the name of the view all trips view
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    @GetMapping("/report/all")
    public String viewAllTrips(Model model) {
        List<TripReportRow> reportData = tripReportDAO.getAllTrips();

        // Calculate summary statistics
        double totalCombinedCost = 0;
        for (TripReportRow row : reportData) {
            totalCombinedCost += row.getTotalCost();
        }
        double averageCost = reportData.isEmpty() ? 0 : totalCombinedCost / reportData.size();

        model.addAttribute("reportData", reportData);
        model.addAttribute("totalTrips", reportData.size());
        model.addAttribute("totalCombinedCost", totalCombinedCost);
        model.addAttribute("averageCost", averageCost);
        model.addAttribute("reportTitle", "View All Trips");

        return "reportAllTrips";
    }

    /**
     * Shows the report input form where the user can select a destination from a dropdown.
     *
     * @param model Spring Model to pass data to the view
     * @return the name of the report input view
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    @GetMapping("/report")
    public String showReportInput(Model model) {
        // Populate dropdown with distinct destinations from the database
        model.addAttribute("destinations", tripDAO.findDistinctDestinations());
        return "reportInput";
    }

    /**
     * Processes the report by invoking the DAO to run the report logic,
     * then sends the user to the view to display the results.
     * Also writes the report results to a file.
     *
     * @param destination  selected destination filter
     * @param model        Spring Model to pass data to the view
     * @return the name of the report results view
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    @PostMapping("/report")
    public String processReport(
            @RequestParam(required = false) String destination,
            Model model) {

        // Get report data from DAO filtered by destination
        List<TripReportRow> reportData = tripReportDAO.getTripSummaryByDestination(destination);

        // Calculate summary statistics
        double totalCombinedCost = 0;
        for (TripReportRow row : reportData) {
            totalCombinedCost += row.getTotalCost();
        }

        double averageCost = reportData.isEmpty() ? 0 : totalCombinedCost / reportData.size();

        // Add data to model
        model.addAttribute("reportData", reportData);
        model.addAttribute("totalTrips", reportData.size());
        model.addAttribute("totalCombinedCost", totalCombinedCost);
        model.addAttribute("averageCost", averageCost);
        model.addAttribute("destination", destination);
        model.addAttribute("destinations", tripDAO.findDistinctDestinations());

        // Write report to file
        writeReportToFile(reportData, totalCombinedCost, averageCost, destination);

        return "reportResults";
    }

    /**
     * Writes the report results to a file in the c:\cis2232\ folder.
     * The filename is based on the report type and the current date/time.
     *
     * @param reportData       the list of trip report rows
     * @param totalCombinedCost total cost of all trips in the report
     * @param averageCost      average cost per trip
     * @param destination      destination filter used
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    private void writeReportToFile(List<TripReportRow> reportData, double totalCombinedCost,
                                   double averageCost, String destination) {
        String fileName = FILE_PREFIX + getCurrentTimestamp() + ".txt";
        String fullPath = FILE_PATH + fileName;

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullPath))) {
            // Header
            writer.println("==============================================================");
            writer.println("           TRAVEL BUDGET PLANNER - TRIP SUMMARY REPORT");
            writer.println("==============================================================");
            writer.println("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println();

            // Filter criteria
            writer.println("Filter Criteria:");
            writer.println("  Destination: " + (destination != null && !destination.trim().isEmpty() ? destination : "All"));
            writer.println();

            // Trip details
            writer.println("--------------------------------------------------------------");
            writer.printf("%-5s %-20s %-10s %-8s %-15s %-15s%n",
                    "ID", "Destination", "Travelers", "Days", "Total Cost", "Cost/Person");
            writer.println("--------------------------------------------------------------");

            for (TripReportRow row : reportData) {
                writer.printf("%-5d %-20s %-10d %-8d $%-14.2f $%-14.2f%n",
                        row.getId(),
                        row.getDestinationName(),
                        row.getNumberOfTravelers(),
                        row.getNumberOfDays(),
                        row.getTotalCost(),
                        row.getCostPerPerson());
            }

            writer.println("--------------------------------------------------------------");
            writer.println();

            // Summary statistics
            writer.println("Summary Statistics:");
            writer.println("  Total Trips: " + reportData.size());
            writer.printf("  Total Combined Cost: $%.2f%n", totalCombinedCost);
            writer.printf("  Average Cost Per Trip: $%.2f%n", averageCost);
            writer.println();
            writer.println("==============================================================");

        } catch (IOException e) {
            System.err.println("Error writing report to file: " + e.getMessage());
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
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
