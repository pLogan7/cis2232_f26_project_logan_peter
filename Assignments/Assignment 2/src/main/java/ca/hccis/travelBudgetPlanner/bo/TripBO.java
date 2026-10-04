package ca.hccis.travelBudgetPlanner.bo;

import ca.hccis.travelBudgetPlanner.util.CisUtility;
import ca.hccis.travelBudgetPlanner.entity.Trip;

/**
 * Determines total cost of travel based on trip variables
 *
 * @author Peter Logan
 * @return Total Cost of Trip
 * @since 10/4/2026
 */
public class TripBO {
    //Constants
    private static final double TRAVEL_INSURANCE_PRICE = 149.99;


    public static double calculateTripCost(Trip trip) {
        if (trip == null) {
            return 0;
        }
        //Set insurance price based on input boolean
        double insuranceCost = 0;
        if (trip.isTravelInsurance()) {
            insuranceCost = TRAVEL_INSURANCE_PRICE;
        }

        double flightCost = trip.getFlightCost() * trip.getNumberOfTravelers();

        double foodCost = trip.getFoodCostPerDay()
                * trip.getNumberOfTravelers()
                * trip.getNumberOfDays();

        double hotelCost = trip.getHotelCostPerNight()
                * trip.getNumberOfDays()
                * trip.getNumberOfTravelers();

        double totalCost = flightCost
                + foodCost
                + hotelCost
                + trip.getActivitiesCost()
                + insuranceCost
                + trip.getOtherExpenses()
                + trip.getTransportationCost();

        return totalCost;
    }

}
