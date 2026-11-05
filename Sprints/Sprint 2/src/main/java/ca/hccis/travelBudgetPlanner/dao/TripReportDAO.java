package ca.hccis.travelBudgetPlanner.dao;

import ca.hccis.travelBudgetPlanner.model.TripReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for retrieving trip data using JDBC for report generation.
 * Uses raw JDBC (not Spring Data JPA) as required for Sprint 2.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
@Repository
public class TripReportDAO {

    @Autowired
    private DataSource dataSource;

    /**
     * Retrieves trip summary data filtered by destination.
     *
     * @param destination      destination filter (null or empty = no filter)
     * @return list of TripReportRow objects matching the criteria
     *
     * @author Peter Logan
     * @since 2026-10-04
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
                    TripReportRow row = new TripReportRow();
                    row.setId(rs.getInt("id"));
                    row.setDestinationName(rs.getString("destinationName"));
                    row.setNumberOfTravelers(rs.getInt("numberOfTravelers"));
                    row.setNumberOfDays(rs.getInt("numberOfDays"));
                    row.setTotalCost(rs.getDouble("totalCost"));

                    // Calculate cost per person
                    double costPerPerson = rs.getDouble("totalCost") / rs.getInt("numberOfTravelers");
                    row.setCostPerPerson(costPerPerson);

                    results.add(row);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving trip summary data", e);
        }

        return results;
    }

    /**
     * Retrieves all trips without any filtering.
     *
     * @return list of all TripReportRow objects
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    public List<TripReportRow> getAllTrips() {
        return getTripSummaryByDestination(null);
    }
}
