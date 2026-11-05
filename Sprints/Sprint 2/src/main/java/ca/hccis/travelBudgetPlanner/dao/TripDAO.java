package ca.hccis.travelBudgetPlanner.dao;

import ca.hccis.travelBudgetPlanner.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Trip entity using raw JDBC.
 * Handles CRUD operations for the Trip table.
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
@Repository
public class TripDAO {

    @Autowired
    private DataSource dataSource;

    /**
     * Retrieves all trips from the database.
     *
     * @return list of all Trip objects
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();
        String sql = "SELECT * FROM Trip ORDER BY id ASC";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                trips.add(mapResultSetToTrip(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all trips", e);
        }

        return trips;
    }

    /**
     * Retrieves a single trip by its ID.
     *
     * @param id the primary key of the trip
     * @return the Trip object, or null if not found
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public Trip findById(Integer id) {
        String sql = "SELECT * FROM Trip WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTrip(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving trip by ID: " + id, e);
        }

        return null;
    }

    /**
     * Checks if a travel ID already exists in the database.
     *
     * @param travelId the travel ID to check
     * @return true if the travel ID exists, false otherwise
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public boolean existsByTravelId(Integer travelId) {
        String sql = "SELECT COUNT(*) FROM Trip WHERE travelId = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, travelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking travel ID existence", e);
        }

        return false;
    }

    /**
     * Inserts a new trip into the database.
     *
     * @param trip the Trip object to insert
     * @return true if the insert was successful
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public boolean save(Trip trip) {
        String sql = "INSERT INTO Trip (travelId, destinationId, destinationName, numberOfTravelers, " +
                "numberOfDays, flightCost, hotelCostPerNight, foodCostPerDay, transportationCost, " +
                "activitiesCost, travelInsurance, otherExpenses, totalCost) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, trip.getTravelId());
            pstmt.setInt(2, trip.getDestinationId());
            pstmt.setString(3, trip.getDestinationName());
            pstmt.setInt(4, trip.getNumberOfTravelers());
            pstmt.setInt(5, trip.getNumberOfDays());
            pstmt.setDouble(6, trip.getFlightCost());
            pstmt.setDouble(7, trip.getHotelCostPerNight());
            pstmt.setDouble(8, trip.getFoodCostPerDay());
            pstmt.setDouble(9, trip.getTransportationCost());
            pstmt.setDouble(10, trip.getActivitiesCost());
            pstmt.setBoolean(11, trip.getTravelInsurance());
            pstmt.setDouble(12, trip.getOtherExpenses());
            pstmt.setDouble(13, trip.getTotalCost());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        trip.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving trip", e);
        }

        return false;
    }

    /**
     * Updates an existing trip in the database.
     *
     * @param trip the Trip object with updated values
     * @return true if the update was successful
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public boolean update(Trip trip) {
        String sql = "UPDATE Trip SET travelId = ?, destinationId = ?, destinationName = ?, " +
                "numberOfTravelers = ?, numberOfDays = ?, flightCost = ?, hotelCostPerNight = ?, " +
                "foodCostPerDay = ?, transportationCost = ?, activitiesCost = ?, " +
                "travelInsurance = ?, otherExpenses = ?, totalCost = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trip.getTravelId());
            pstmt.setInt(2, trip.getDestinationId());
            pstmt.setString(3, trip.getDestinationName());
            pstmt.setInt(4, trip.getNumberOfTravelers());
            pstmt.setInt(5, trip.getNumberOfDays());
            pstmt.setDouble(6, trip.getFlightCost());
            pstmt.setDouble(7, trip.getHotelCostPerNight());
            pstmt.setDouble(8, trip.getFoodCostPerDay());
            pstmt.setDouble(9, trip.getTransportationCost());
            pstmt.setDouble(10, trip.getActivitiesCost());
            pstmt.setBoolean(11, trip.getTravelInsurance());
            pstmt.setDouble(12, trip.getOtherExpenses());
            pstmt.setDouble(13, trip.getTotalCost());
            pstmt.setInt(14, trip.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating trip", e);
        }
    }

    /**
     * Deletes a trip from the database by its ID.
     *
     * @param id the primary key of the trip to delete
     * @return true if the delete was successful
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public boolean delete(Integer id) {
        String sql = "DELETE FROM Trip WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting trip with ID: " + id, e);
        }
    }

    /**
     * Retrieves all distinct destination names from the database.
     *
     * @return list of distinct destination names
     *
     * @author Peter Logan
     * @since 2026-10-04
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
     * Maps a ResultSet row to a Trip object.
     *
     * @param rs the ResultSet positioned at a valid row
     * @return a Trip object populated with the row data
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    private Trip mapResultSetToTrip(ResultSet rs) throws SQLException {
        Trip trip = new Trip();
        trip.setId(rs.getInt("id"));
        trip.setTravelId(rs.getInt("travelId"));
        trip.setDestinationId(rs.getInt("destinationId"));
        trip.setDestinationName(rs.getString("destinationName"));
        trip.setNumberOfTravelers(rs.getInt("numberOfTravelers"));
        trip.setNumberOfDays(rs.getInt("numberOfDays"));
        trip.setFlightCost(rs.getDouble("flightCost"));
        trip.setHotelCostPerNight(rs.getDouble("hotelCostPerNight"));
        trip.setFoodCostPerDay(rs.getDouble("foodCostPerDay"));
        trip.setTransportationCost(rs.getDouble("transportationCost"));
        trip.setActivitiesCost(rs.getDouble("activitiesCost"));
        trip.setTravelInsurance(rs.getBoolean("travelInsurance"));
        trip.setOtherExpenses(rs.getDouble("otherExpenses"));
        trip.setTotalCost(rs.getDouble("totalCost"));
        return trip;
    }
}
