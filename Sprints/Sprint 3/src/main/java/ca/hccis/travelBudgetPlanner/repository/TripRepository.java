package ca.hccis.travelBudgetPlanner.repository;

import ca.hccis.travelBudgetPlanner.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA Repository for the Trip entity.
 * Provides CRUD operations and custom query methods.
 *
 * @author Peter Logan
 * @since 2026-11-05
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    /**
     * Find all trips by destination name (case-insensitive, partial match).
     *
     * @param destinationName the destination name to search for
     * @return list of matching trips
     */
    List<Trip> findByDestinationNameContainingIgnoreCase(String destinationName);

    /**
     * Find all trips by number of travelers.
     *
     * @param numberOfTravelers the exact number of travelers
     * @return list of matching trips
     */
    List<Trip> findByNumberOfTravelers(Integer numberOfTravelers);

    /**
     * Find all trips where travel insurance was purchased.
     *
     * @param travelInsurance the insurance flag
     * @return list of matching trips
     */
    List<Trip> findByTravelInsurance(Boolean travelInsurance);

    /**
     * Check if a travel ID already exists.
     *
     * @param travelId the travel ID to check
     * @return true if the travel ID exists
     */
    boolean existsByTravelId(Integer travelId);

    /**
     * Find a trip by its travel ID.
     *
     * @param travelId the travel ID to search for
     * @return the trip with the given travel ID, or null if not found
     */
    Trip findByTravelId(Integer travelId);
}
