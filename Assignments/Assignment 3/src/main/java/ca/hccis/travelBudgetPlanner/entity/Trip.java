package ca.hccis.travelBudgetPlanner.entity;

/**
 * Represents a single trip entry for the Travel Budget Planner application.
 * Shared between console and JOptionPane threads.
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
public class Trip {

    // Instance Variables
    private int id;
    private String destination;
    private int numberOfTravelers;
    private int numberOfDays;
    private double flightCost;
    private double hotelCostPerNight;
    private double foodCostPerDay;
    private double transportationCost;
    private double activitiesCost;
    private boolean travelInsurance;
    private double otherExpenses;
    private double totalCost;

    // Constructors
    public Trip() {
    }

    public Trip(int id, String destination, int numberOfTravelers, int numberOfDays,
                double flightCost, double hotelCostPerNight, double foodCostPerDay,
                double transportationCost, double activitiesCost,
                boolean travelInsurance, double otherExpenses, double totalCost) {

        this.id = id;
        this.destination = destination;
        this.numberOfTravelers = numberOfTravelers;
        this.numberOfDays = numberOfDays;
        this.flightCost = flightCost;
        this.hotelCostPerNight = hotelCostPerNight;
        this.foodCostPerDay = foodCostPerDay;
        this.transportationCost = transportationCost;
        this.activitiesCost = activitiesCost;
        this.travelInsurance = travelInsurance;
        this.otherExpenses = otherExpenses;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(double flightCost) {
        this.flightCost = flightCost;
    }

    public double getHotelCostPerNight() {
        return hotelCostPerNight;
    }

    public void setHotelCostPerNight(double hotelCostPerNight) {
        this.hotelCostPerNight = hotelCostPerNight;
    }

    public double getFoodCostPerDay() {
        return foodCostPerDay;
    }

    public void setFoodCostPerDay(double foodCostPerDay) {
        this.foodCostPerDay = foodCostPerDay;
    }

    public double getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(double transportationCost) {
        this.transportationCost = transportationCost;
    }

    public double getActivitiesCost() {
        return activitiesCost;
    }

    public void setActivitiesCost(double activitiesCost) {
        this.activitiesCost = activitiesCost;
    }

    public boolean isTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(boolean travelInsurance) {
        this.travelInsurance = travelInsurance;
    }

    public double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Returns a string representation of the Trip object.
     *
     * @return Formatted string with all trip details.
     *
     * @author Peter Logan
     * @since 2026-10-04
     */
    @Override
    public String toString() {
        return String.format(
                "Trip: id=%d, destination='%s', travelers=%d, days=%d, " +
                        "flightCost=%.2f, hotelCostPerNight=%.2f, foodCostPerDay=%.2f, " +
                        "transportationCost=%.2f, activitiesCost=%.2f, " +
                        "travelInsurance=%b, otherExpenses=%.2f, totalCost=%.2f",
                id, destination, numberOfTravelers, numberOfDays,
                flightCost, hotelCostPerNight, foodCostPerDay,
                transportationCost, activitiesCost,
                travelInsurance, otherExpenses, totalCost
        );
    }
}
