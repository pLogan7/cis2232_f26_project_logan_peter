package ca.hccis.travelBudgetPlanner.repositories;

import ca.hccis.travelBudgetPlanner.jpa.entity.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//This is a spring data repository

@Repository
public interface TravelPlannerRepository extends CrudRepository<Trip, Integer> {
    /**
     * Use Spring Data JPA functionality to locate a list that has the string which was passed as a parameter.
     * @param destination The name of the destination.
     * @return The list of trips with that destination
     * @author PRL
     * @since 10 26 2026
     */
    @Query("SELECT t FROM Trip t WHERE t.destinationName LIKE %:destination%")
    List<Trip> findByDestinationName(@Param("destination") String destination);
}
