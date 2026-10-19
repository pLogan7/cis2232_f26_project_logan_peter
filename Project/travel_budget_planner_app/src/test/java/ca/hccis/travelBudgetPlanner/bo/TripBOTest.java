/**
 * Unit Testing used to test the validity of the TripBO Calculate Trip Cost main calculation
 *
 * @author Peter Logan
 */
package ca.hccis.travelBudgetPlanner.bo;

import ca.hccis.travelBudgetPlanner.entity.Trip;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripBOTest {

    // Doubles can't be compared exactly, so assertEquals needs a tolerance.
    private static final double DELTA = 0.001;

    /**
     * Builds a custom trip with minimal values, so calculation is easy and tests edge cases.
     * @param insurance true for additional cost or false for no cost
     * @return trip object with those values
     */
    private Trip buildCustomTrip(boolean insurance){
        Trip trip = new Trip();
        trip.setFlightCost(1); //Set Flight Cost based on Destination ID of Tokyo, Japan ($1950)
        trip.setNumberOfTravelers(1);
        trip.setNumberOfDays(1);
        trip.setHotelCostPerNight(1);
        trip.setFoodCostPerDay(1);
        trip.setTransportationCost(1);
        trip.setActivitiesCost(1);
        trip.setOtherExpenses(1);
        trip.setTravelInsurance(insurance);

        return trip;
    }

    //Tests that the addition of insurance indeed yields a higher cost
    @Test
    void calculateTripCost_withInsurance_costsMoreThanWithout() {
        double without = TripBO.calculateTripCost(buildCustomTrip(false));
        double with = TripBO.calculateTripCost(buildCustomTrip(true));

        assertTrue(with > without, "Insurance should increase the total cost!");
    }
    //Tests that out of bounds destination ID throws an exception
    @Test
    void setDestinationName_badId_throwsException() {
        Trip trip = new Trip();

        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> trip.setDestinationName(99));
    }

    @Test
    void calculateCustomTripCost_noInsurance_returnsExpectedTotal() {
        // flight:     $1950 * 1          = $1950
        // food:       1 * 1 * 1       =  1
        // hotel:      1 * 1 * 1      =  1
        // activities:                  =  1
        // other:                       =   1
        // transport:                   =   1
        // total                        = $1955
        Trip trip = buildCustomTrip(false);

        assertEquals(1955.00, TripBO.calculateTripCost(trip), DELTA);
    }


    //****************************************************************************
    //The following were the unit tests that were created by Claude Code AI
    //****************************************************************************

    /**
     * Builds a trip with known values so the expected totals are easy to work out by hand.
     * Flight cost comes from DESTINATION_PRICES: destination 5 (Toronto) = 550.
     */
    private Trip buildBasicTrip(boolean insurance) {
        Trip trip = new Trip();
        trip.setFlightCost(5);            // NOTE: takes a destination ID, not a dollar amount
        trip.setNumberOfTravelers(2);
        trip.setNumberOfDays(4);
        trip.setHotelCostPerNight(100);
        trip.setFoodCostPerDay(50);
        trip.setTransportationCost(80);
        trip.setActivitiesCost(200);
        trip.setOtherExpenses(30);
        trip.setTravelInsurance(insurance);
        return trip;
    }

    @Test
    void calculateTripCost_nullTrip_returnsZero() {
        assertEquals(0, TripBO.calculateTripCost(null), DELTA);
    }

    @Test
    void calculateTripCost_emptyTrip_returnsZero() {
        assertEquals(0, TripBO.calculateTripCost(new Trip()), DELTA);
    }

    @Test
    void calculateTripCost_noInsurance_returnsExpectedTotal() {
        // flight:     550 * 2          = 1100
        // food:       50 * 2 * 4       =  400
        // hotel:      100 * 4 * 2      =  800
        // activities:                  =  200
        // other:                       =   30
        // transport:                   =   80
        // total                        = 2610
        Trip trip = buildBasicTrip(false);

        assertEquals(2610.00, TripBO.calculateTripCost(trip), DELTA);
    }

    @Test
    void calculateTripCost_withInsurance_addsInsurancePrice() {
        Trip trip = buildBasicTrip(true);

        assertEquals(2759.99, TripBO.calculateTripCost(trip), DELTA);
    }

    @Test
    void calculateTripCost_insuranceDifference_equalsInsurancePrice() {
        double without = TripBO.calculateTripCost(buildBasicTrip(false));
        double with = TripBO.calculateTripCost(buildBasicTrip(true));

        assertEquals(149.99, with - without, DELTA);
    }

    @Test
    void calculateTripCost_zeroDays_onlyChargesPerTravelerAndFlatCosts() {
        Trip trip = buildBasicTrip(false);
        trip.setNumberOfDays(0);

        // Hotel and food drop to 0. Remaining: 1100 + 200 + 30 + 80 = 1410
        assertEquals(1410.00, TripBO.calculateTripCost(trip), DELTA);
    }

    @Test
    void calculateTripCost_doublingTravelers_doublesPerPersonCostsOnly() {
        Trip oneTraveler = new Trip();
        oneTraveler.setFlightCost(5);
        oneTraveler.setNumberOfTravelers(1);
        oneTraveler.setNumberOfDays(1);
        oneTraveler.setHotelCostPerNight(100);
        oneTraveler.setFoodCostPerDay(50);

        Trip twoTravelers = new Trip();
        twoTravelers.setFlightCost(5);
        twoTravelers.setNumberOfTravelers(2);
        twoTravelers.setNumberOfDays(1);
        twoTravelers.setHotelCostPerNight(100);
        twoTravelers.setFoodCostPerDay(50);

        // 550 + 100 + 50 = 700 for one person, so 1400 for two
        assertEquals(700.00, TripBO.calculateTripCost(oneTraveler), DELTA);
        assertEquals(1400.00, TripBO.calculateTripCost(twoTravelers), DELTA);
    }
}