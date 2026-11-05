package ca.hccis.travelBudgetPlanner.bo;

import ca.hccis.travelBudgetPlanner.entity.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the TripBO.calculateTripCost() method.
 * Tests 1-3 were created following a Test Driven Development (TDD) approach.
 * Tests 4+ were generated using AI based on the project requirements and entity class.
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
class TripBOTest {

    // =========================================================================
    // Tests 1-3: Created by Peter Logan following Test Driven Development (TDD)
    // =========================================================================

    /**
     * Test 1 created by Peter Logan following TDD.
     * Tests a basic trip with travel insurance enabled.
     * Expected: flightCost*travelers + hotelCost*days*travelers + foodCost*travelers*days
     *           + transportation + activities + insurance + otherExpenses
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    @Test
    void testCalculateTripCost_WithInsurance() {
        // Arrange
        Trip trip = new Trip();
        trip.setDestination("Paris, France");
        trip.setNumberOfTravelers(2);
        trip.setNumberOfDays(7);
        trip.setFlightCost(500.00);
        trip.setHotelCostPerNight(180.00);
        trip.setFoodCostPerDay(60.00);
        trip.setTransportationCost(150.00);
        trip.setActivitiesCost(400.00);
        trip.setTravelInsurance(true);
        trip.setOtherExpenses(100.00);

        // Expected:
        // Flight: 500 * 2 = 1000
        // Hotel: 180 * 7 * 2 = 2520
        // Food: 60 * 2 * 7 = 840
        // Transportation: 150
        // Activities: 400
        // Insurance: 149.99
        // Other: 100
        // Total: 1000 + 2520 + 840 + 150 + 400 + 149.99 + 100 = 5159.99
        double expected = 5159.99;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Total trip cost calculation with insurance should match expected value");
    }

    /**
     * Test 2 created by Peter Logan following TDD.
     * Tests a trip without travel insurance to ensure insurance cost is not added.
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    @Test
    void testCalculateTripCost_WithoutInsurance() {
        // Arrange
        Trip trip = new Trip();
        trip.setDestination("Toronto, Canada");
        trip.setNumberOfTravelers(4);
        trip.setNumberOfDays(4);
        trip.setFlightCost(275.00);
        trip.setHotelCostPerNight(120.00);
        trip.setFoodCostPerDay(45.00);
        trip.setTransportationCost(80.00);
        trip.setActivitiesCost(300.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(50.00);

        // Expected:
        // Flight: 275 * 4 = 1100
        // Hotel: 120 * 4 * 4 = 1920
        // Food: 45 * 4 * 4 = 720
        // Transportation: 80
        // Activities: 300
        // Insurance: 0
        // Other: 50
        // Total: 1100 + 1920 + 720 + 80 + 300 + 0 + 50 = 4170
        double expected = 4170.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Total trip cost without insurance should not include insurance fee");
    }

    /**
     * Test 3 created by Peter Logan following TDD.
     * Tests edge case where a null trip is passed to the calculate method.
     * Expected: returns 0 and does not throw an exception.
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    @Test
    void testCalculateTripCost_NullTrip() {
        // Act
        double actual = TripBO.calculateTripCost(null);

        // Assert
        assertEquals(0, actual, 0.01,
                "Null trip should return 0 cost");
        assertTrue(actual >= 0,
                "Cost returned for null trip should be non-negative");
    }

    // =========================================================================
    // The following tests were generated using AI (Claude) based on the
    // project requirements and the Trip entity class definition.
    // =========================================================================

    /**
     * AI-Generated Test: Single traveler, single day trip with minimal costs.
     */
    @Test
    void testCalculateTripCost_SingleTravelerSingleDay() {
        // Arrange
        Trip trip = new Trip();
        trip.setDestination("Montreal, Canada");
        trip.setNumberOfTravelers(1);
        trip.setNumberOfDays(1);
        trip.setFlightCost(100.00);
        trip.setHotelCostPerNight(80.00);
        trip.setFoodCostPerDay(30.00);
        trip.setTransportationCost(20.00);
        trip.setActivitiesCost(50.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 100 + 80 + 30 + 20 + 50 + 0 + 0 = 280
        double expected = 280.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Single traveler single day trip cost should be calculated correctly");
    }

    /**
     * AI-Generated Test: Large group trip with high costs and insurance.
     */
    @Test
    void testCalculateTripCost_LargeGroup() {
        // Arrange
        Trip trip = new Trip();
        trip.setDestination("Tokyo, Japan");
        trip.setNumberOfTravelers(10);
        trip.setNumberOfDays(14);
        trip.setFlightCost(1200.00);
        trip.setHotelCostPerNight(200.00);
        trip.setFoodCostPerDay(75.00);
        trip.setTransportationCost(500.00);
        trip.setActivitiesCost(2000.00);
        trip.setTravelInsurance(true);
        trip.setOtherExpenses(300.00);

        // Expected:
        // Flight: 1200 * 10 = 12000
        // Hotel: 200 * 14 * 10 = 28000
        // Food: 75 * 10 * 14 = 10500
        // Transportation: 500
        // Activities: 2000
        // Insurance: 149.99
        // Other: 300
        // Total: 12000 + 28000 + 10500 + 500 + 2000 + 149.99 + 300 = 53449.99
        double expected = 53449.99;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Large group trip cost should be calculated correctly");
        assertTrue(actual > 50000,
                "Large group trip cost should exceed 50000");
    }

    /**
     * AI-Generated Test: Trip with zero values for optional expenses.
     */
    @Test
    void testCalculateTripCost_ZeroOptionalExpenses() {
        // Arrange
        Trip trip = new Trip();
        trip.setDestination("Local City");
        trip.setNumberOfTravelers(2);
        trip.setNumberOfDays(3);
        trip.setFlightCost(0.00);
        trip.setHotelCostPerNight(0.00);
        trip.setFoodCostPerDay(0.00);
        trip.setTransportationCost(0.00);
        trip.setActivitiesCost(0.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 0 + 0 + 0 + 0 + 0 + 0 + 0 = 0
        double expected = 0.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Trip with all zero expenses should return 0 cost");
    }

    /**
     * AI-Generated Test: Verify that hotel cost scales correctly with travelers and days.
     */
    @Test
    void testCalculateTripCost_HotelCostScaling() {
        // Arrange - isolate hotel cost by setting other costs to 0
        Trip trip = new Trip();
        trip.setDestination("Test Destination");
        trip.setNumberOfTravelers(3);
        trip.setNumberOfDays(5);
        trip.setFlightCost(0.00);
        trip.setHotelCostPerNight(100.00);
        trip.setFoodCostPerDay(0.00);
        trip.setTransportationCost(0.00);
        trip.setActivitiesCost(0.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 100 * 5 * 3 = 1500
        double expected = 1500.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Hotel cost should scale with both travelers and days");
    }

    /**
     * AI-Generated Test: Verify that food cost scales correctly with travelers and days.
     */
    @Test
    void testCalculateTripCost_FoodCostScaling() {
        // Arrange - isolate food cost by setting other costs to 0
        Trip trip = new Trip();
        trip.setDestination("Test Destination");
        trip.setNumberOfTravelers(5);
        trip.setNumberOfDays(4);
        trip.setFlightCost(0.00);
        trip.setHotelCostPerNight(0.00);
        trip.setFoodCostPerDay(50.00);
        trip.setTransportationCost(0.00);
        trip.setActivitiesCost(0.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 50 * 5 * 4 = 1000
        double expected = 1000.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Food cost should scale with both travelers and days");
    }

    /**
     * AI-Generated Test: Verify that flight cost scales correctly with travelers only.
     */
    @Test
    void testCalculateTripCost_FlightCostScaling() {
        // Arrange - isolate flight cost by setting other costs to 0
        Trip trip = new Trip();
        trip.setDestination("Test Destination");
        trip.setNumberOfTravelers(6);
        trip.setNumberOfDays(10);
        trip.setFlightCost(300.00);
        trip.setHotelCostPerNight(0.00);
        trip.setFoodCostPerDay(0.00);
        trip.setTransportationCost(0.00);
        trip.setActivitiesCost(0.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 300 * 6 = 1800 (days should not affect flight cost)
        double expected = 1800.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Flight cost should scale with travelers only, not days");
    }

    /**
     * AI-Generated Test: Verify insurance is applied as a flat rate regardless of travelers.
     */
    @Test
    void testCalculateTripCost_InsuranceFlatRate() {
        // Arrange - isolate insurance by setting other costs to 0
        Trip trip = new Trip();
        trip.setDestination("Test Destination");
        trip.setNumberOfTravelers(8);
        trip.setNumberOfDays(10);
        trip.setFlightCost(0.00);
        trip.setHotelCostPerNight(0.00);
        trip.setFoodCostPerDay(0.00);
        trip.setTransportationCost(0.00);
        trip.setActivitiesCost(0.00);
        trip.setTravelInsurance(true);
        trip.setOtherExpenses(0.00);

        // Expected: 149.99 (flat rate, not multiplied by travelers)
        double expected = 149.99;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Insurance should be a flat rate regardless of number of travelers");
    }

    /**
     * AI-Generated Test: Verify transportation cost is treated as a total (not per person).
     */
    @Test
    void testCalculateTripCost_TransportationAsTotal() {
        // Arrange - isolate transportation cost
        Trip trip = new Trip();
        trip.setDestination("Test Destination");
        trip.setNumberOfTravelers(4);
        trip.setNumberOfDays(5);
        trip.setFlightCost(0.00);
        trip.setHotelCostPerNight(0.00);
        trip.setFoodCostPerDay(0.00);
        trip.setTransportationCost(200.00);
        trip.setActivitiesCost(0.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 200 (not multiplied by travelers or days)
        double expected = 200.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Transportation cost should be treated as a total, not per person");
    }

    /**
     * AI-Generated Test: Verify activities cost is treated as a total (not per person).
     */
    @Test
    void testCalculateTripCost_ActivitiesAsTotal() {
        // Arrange - isolate activities cost
        Trip trip = new Trip();
        trip.setDestination("Test Destination");
        trip.setNumberOfTravelers(3);
        trip.setNumberOfDays(7);
        trip.setFlightCost(0.00);
        trip.setHotelCostPerNight(0.00);
        trip.setFoodCostPerDay(0.00);
        trip.setTransportationCost(0.00);
        trip.setActivitiesCost(500.00);
        trip.setTravelInsurance(false);
        trip.setOtherExpenses(0.00);

        // Expected: 500 (not multiplied by travelers or days)
        double expected = 500.00;

        // Act
        double actual = TripBO.calculateTripCost(trip);

        // Assert
        assertEquals(expected, actual, 0.01,
                "Activities cost should be treated as a total, not per person");
    }
}
