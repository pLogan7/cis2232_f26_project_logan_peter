package ca.hccis.travelBudgetPlanner.dao;

import ca.hccis.travelBudgetPlanner.model.TripReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for retrieving trip data using JDBC for report generation.
 * Uses raw JDBC (not Spring Data JPA) for custom report queries.
 *
 * @author Peter Logan
 * @since 2026-11-05
 */
@Repository
public class TripReportDAO {

    @Autowired
    private DataSource dataSource;

    // =========================================================================
    // DESTINATION QUERIES
    // =========================================================================

    /**
     * Retrieves trip summary data filtered by destination.
     *
     * @param destination optional destination filter (null or empty = no filter)
     * @return list of TripReportRow objects matching the criteria
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    public List<TripReportRow> getTripSummaryByDestination(String destination) {
        List<TripReportRow> results = new ArrayList<>();

        String sql = "SELECT id, destinationName, numberOfTravelers, numberOfDays, totalCost " +
                "FROM Trip WHERE 1=1";

        List<Object> params = new ArrayList<>();

        if (destination != null && !destination.trim().isEmpty()) {
            sql += " AND destinationName = ?";
            params.add(destination);
        }

        sql += " ORDER BY id ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TripReportRow row = mapResultSetToReportRow(rs);
                    results.add(row);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving trip summary data", e);
        }

        return results;
    }

    // =========================================================================
    // TRAVELER QUERIES
    // =========================================================================

    /**
     * Retrieves trip summary data filtered by exact number of travelers.
     *
     * @param numberOfTravelers exact number of travelers filter (null = no filter)
     * @return list of TripReportRow objects matching the criteria
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    public List<TripReportRow> getTripSummaryByTravelers(Integer numberOfTravelers) {
        List<TripReportRow> results = new ArrayList<>();

        String sql = "SELECT id, destinationName, numberOfTravelers, numberOfDays, totalCost " +
                "FROM Trip WHERE 1=1";

        List<Object> params = new ArrayList<>();

        if (numberOfTravelers != null && numberOfTravelers > 0) {
            sql += " AND numberOfTravelers = ?";
            params.add(numberOfTravelers);
        }

        sql += " ORDER BY id ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    TripReportRow row = mapResultSetToReportRow(rs);
                    results.add(row);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving trip summary by travelers data", e);
        }

        return results;
    }

    // =========================================================================
    // GENERAL QUERIES
    // =========================================================================

    /**
     * Retrieves all trips without any filtering.
     *
     * @return list of all TripReportRow objects
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    public List<TripReportRow> getAllTrips() {
        return getTripSummaryByDestination(null);
    }

    // =========================================================================
    // DISTINCT LOOKUP QUERIES
    // =========================================================================

    /**
     * Retrieves all distinct destination names from the database.
     *
     * @return list of distinct destination names
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    public List<String> findDistinctDestinations() {
        List<String> destinations = new ArrayList<>();
        String sql = "SELECT DISTINCT destinationName FROM Trip ORDER BY destinationName ASC";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                destinations.add(rs.getString("destinationName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving distinct destinations", e);
        }

        return destinations;
    }

    /**
     * Retrieves all distinct numbers of travelers from the database.
     *
     * @return list of distinct traveler counts ordered ascending
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    public List<Integer> findDistinctNumberOfTravelers() {
        List<Integer> travelerCounts = new ArrayList<>();
        String sql = "SELECT DISTINCT numberOfTravelers FROM Trip ORDER BY numberOfTravelers ASC";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                travelerCounts.add(rs.getInt("numberOfTravelers"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving distinct number of travelers", e);
        }

        return travelerCounts;
    }

    // =========================================================================
    // HELPER METHODS
    // =========================================================================

    /**
     * Maps a ResultSet row to a TripReportRow object.
     *
     * @param rs the ResultSet positioned at a valid row
     * @return a TripReportRow object populated with the row data
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    private TripReportRow mapResultSetToReportRow(ResultSet rs) throws SQLException {
        TripReportRow row = new TripReportRow();
        row.setId(rs.getInt("id"));
        row.setDestinationName(rs.getString("destinationName"));
        row.setNumberOfTravelers(rs.getInt("numberOfTravelers"));
        row.setNumberOfDays(rs.getInt("numberOfDays"));
        row.setTotalCost(rs.getDouble("totalCost"));

        // Calculate cost per person
        double costPerPerson = rs.getDouble("totalCost") / rs.getInt("numberOfTravelers");
        row.setCostPerPerson(costPerPerson);

        return row;
    }
}
