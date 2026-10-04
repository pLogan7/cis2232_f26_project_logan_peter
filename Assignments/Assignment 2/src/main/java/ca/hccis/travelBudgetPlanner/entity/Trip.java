/**
 * Class that represents one Trip
 *
 * @author Peter Logan
 * @since 09/21/2026
 */
package ca.hccis.travelBudgetPlanner.entity;

import ca.hccis.travelBudgetPlanner.util.CisUtility;
import ca.hccis.travelBudgetPlanner.util.ExtraUtility;
import ca.hccis.travelBudgetPlanner.bo.TripBO;

public class Trip {

    //Data Fields
    private int id;
    private int travelId;
    private int destinationId;
    private String destinationName;
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

    //constants
    private final int INT_MIN = 1;
    private final int INT_MAX = 100;
    private final double DOUBLE_MIN = 0;
    private final double DOUBLE_MAX = 99999;

    //Array to hold travel destinations
    String[] travelDestinations = {
            "Paris, France",
            "Tokyo, Japan",
            "New York, USA",
            "Rome, Italy",
            "London, England",
            "Toronto, Canada",
            "Sydney, Australia",
            "Madrid, Spain",
            "Cairo, Egypt",
            "Rio de Janeiro, Brazil"
    };

    //Parallel Array to travelDestinations String array of names
    private static final double[] DESTINATION_PRICES = {
            950, //Paris, France
            1950, //Tokyo, Japan
            850, //New York, USA
            1350, //Rome, Italy
            1400, //London, England
            550, //Toronto, Canada
            2700, //Sydney, Australia
            1300, //Madrid, Spain
            1450, //Cairo, Egypt
            1730 //Rio de Janeiro, Brazil
    };

    public Trip() {
    }

    public Trip(int id) {
        this.id = id;
    }

    public Trip(int id, int travelId, String destination, int numberOfTravelers, int numberOfDays) {
        this.id = id;
        this.travelId = travelId;
        this.destinationName = destination;
        this.numberOfTravelers = numberOfTravelers;
        this.numberOfDays = numberOfDays;
    }

    public void getInformation() {
        boolean validInput = true;
        //Uses custom validate integer class
        travelId = ExtraUtility.validateInteger(10000, 99999, "Enter travel ID: ");

        System.out.println("Select Destination from list: ");

        //Builds output based on travelDestinations Array
        StringBuilder destinationsOutput = new StringBuilder();
        for (int i = 0; i < travelDestinations.length; i++) {
            destinationsOutput.append(i).append(" = ").append(travelDestinations[i]).append(" \n");
        }

        destinationId = ExtraUtility.validateInteger(0, travelDestinations.length - 1, "Enter destination ID\n" + destinationsOutput);
        //Assigns destination String and flight cost based on selected id
        destinationName = travelDestinations[destinationId];
        flightCost = DESTINATION_PRICES[destinationId];

        numberOfTravelers = ExtraUtility.validateInteger(INT_MIN, INT_MAX, "Enter number of travelers: ");
        numberOfDays = ExtraUtility.validateInteger(INT_MIN, INT_MAX, "Enter number of Days Traveling: ");


        hotelCostPerNight = ExtraUtility.validateDouble(DOUBLE_MIN, DOUBLE_MAX, "Enter hotel cost (per person, per night): ");
        foodCostPerDay = ExtraUtility.validateDouble(DOUBLE_MIN, DOUBLE_MAX, "Enter food cost (per person, per day): ");

        transportationCost = ExtraUtility.validateDouble(DOUBLE_MIN, DOUBLE_MAX, "Enter other transportation cost (total): ");
        activitiesCost = ExtraUtility.validateDouble(DOUBLE_MIN, DOUBLE_MAX, "Enter activities cost (total): ");

        travelInsurance = CisUtility.getInputBoolean("Will you get travel insurance?: ");

        otherExpenses = ExtraUtility.validateDouble(DOUBLE_MIN, DOUBLE_MAX, "Enter other expenses: ");
    }

    public void edit() {
        String destination = CisUtility.getInputString("Destination: ");
        int numberOfTravelers = CisUtility.getInputInt("Number of travelers: ");
        int numberOfDays = CisUtility.getInputInt("Number of Days: ");

        setDestination(destination);
        setNumberOfTravelers(numberOfTravelers);
        setNumberOfDays(numberOfDays);
    }


    //Getters + Setters


    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public boolean isTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(boolean travelInsurance) {
        this.travelInsurance = travelInsurance;
    }

    public double getActivitiesCost() {
        return activitiesCost;
    }

    public void setActivitiesCost(double activitiesCost) {
        this.activitiesCost = activitiesCost;
    }

    public double getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(double transportationCost) {
        this.transportationCost = transportationCost;
    }

    public double getFoodCostPerDay() {
        return foodCostPerDay;
    }

    public void setFoodCostPerDay(double foodCostPerDay) {
        this.foodCostPerDay = foodCostPerDay;
    }

    public double getHotelCostPerNight() {
        return hotelCostPerNight;
    }

    public void setHotelCostPerNight(double hotelCostPerNight) {
        this.hotelCostPerNight = hotelCostPerNight;
    }

    public double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(int destinationId) {
        this.flightCost = DESTINATION_PRICES[destinationId];
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(int destinationId) {
        this.destinationName = travelDestinations[destinationId];
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Trip: travelId=%d, destination='%s', numberOfTravelers='%s', numberOfDays='%s'",
                travelId, destinationName, numberOfTravelers, numberOfDays
        );
    }

}
