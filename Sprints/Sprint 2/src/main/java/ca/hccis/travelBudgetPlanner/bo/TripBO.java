package ca.hccis.travelBudgetPlanner.bo;

import ca.hccis.travelBudgetPlanner.model.Trip;

/**
 * Determines total cost of travel based on trip variables.
 * Business Object class for the Travel Budget Planner application.
 *
 * Calculation breakdown:
 * - Flight Cost = flightCost * numberOfTravelers
 * - Hotel Cost = hotelCostPerNight * numberOfDays * numberOfTravelers
 * - Food Cost = foodCostPerDay * numberOfTravelers * numberOfDays
 * - Activities Cost = activitiesCost (total)
 * - Transportation Cost = transportationCost (total)
 * - Travel Insurance = $149.99 flat rate if purchased
 * - Other Expenses = otherExpenses (total)
 * - Total Cost = sum of all above
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
public class TripBO {

    // Constants
    private static final double TRAVEL_INSURANCE_PRICE = 149.99;

    /**
     * Calculates the total estimated cost of a trip based on all entered variables.
     *
     * @param trip the Trip object containing all cost variables
     * @return the total estimated cost of the trip
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    public static double calculateTripCost(Trip trip) {
        if (trip == null) {
            return 0;
        }

        // Set insurance price based on input boolean
        double insuranceCost = 0;
        if (Boolean.TRUE.equals(trip.getTravelInsurance())) {
            insuranceCost = TRAVEL_INSURANCE_PRICE;
        }

        int travelers = trip.getNumberOfTravelers() != null ? trip.getNumberOfTravelers() : 0;
        int days = trip.getNumberOfDays() != null ? trip.getNumberOfDays() : 0;

        // Flight cost is total for all travelers
        double flightTotal = (trip.getFlightCost() != null ? trip.getFlightCost() : 0) * travelers;

        // Hotel cost is per person, per night
        double hotelTotal = (trip.getHotelCostPerNight() != null ? trip.getHotelCostPerNight() : 0) * days * travelers;

        // Food cost is per person, per day
        double foodTotal = (trip.getFoodCostPerDay() != null ? trip.getFoodCostPerDay() : 0) * travelers * days;

        // Sum all costs
        double totalCost = flightTotal
                + hotelTotal
                + foodTotal
                + (trip.getTransportationCost() != null ? trip.getTransportationCost() : 0)
                + (trip.getActivitiesCost() != null ? trip.getActivitiesCost() : 0)
                + insuranceCost
                + (trip.getOtherExpenses() != null ? trip.getOtherExpenses() : 0);

        return totalCost;
    }
}
