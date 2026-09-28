/**
 * Class that represents one Trip
 * @author Peter Logan
 * @since 09/21/2026
 */
package ca.hccis.files.entity;

import java.util.Scanner;

public class Trip {

    //Data Fields
    private int id;
    private int travelId;
    private String destination;
    private int numberOfTravelers;
    private int numberOfDays;
    private double flightCost;
    private double hotelCostPerNight;
    private double foodCostPerDay;
    private double transportationCost;
    private double activitiesCost;
    private double travelInsurance;
    private double otherExpenses;
    private double totalCost;

    public Trip() {
    }

    public Trip(int id) {
        this.id = id;
    }

    public Trip(int id, int travelId, String destination, int numberOfTravelers, int numberOfDays) {
        this.id = id;
        this.travelId = travelId;
        this.destination = destination;
        this.numberOfTravelers = numberOfTravelers;
        this.numberOfDays = numberOfDays;
    }

    public void getInformation() {
        travelId = ca.hccis.util.CisUtility.getInputInt("Enter unique travel ID: ");
        destination = ca.hccis.util.CisUtility.getInputString("Enter destination: ");
        numberOfTravelers = ca.hccis.util.CisUtility.getInputInt("Enter number of travelers: ");
        numberOfDays = ca.hccis.util.CisUtility.getInputInt("Enter number days traveling: ");
        flightCost = ca.hccis.util.CisUtility.getInputDouble("Enter flight cost (per traveler): ");
        hotelCostPerNight = ca.hccis.util.CisUtility.getInputDouble("Enter hotel cost (per person, per night): ");
        foodCostPerDay = ca.hccis.util.CisUtility.getInputDouble("Enter food cost (per person, per day): ");
        transportationCost = ca.hccis.util.CisUtility.getInputDouble("Enter other transportation cost (total): ");

    }

    public void edit(){
        String destination = ca.hccis.util.CisUtility.getInputString("Destination: ");
        int numberOfTravelers = ca.hccis.util.CisUtility.getInputInt("Number of travelers: ");
        int numberOfDays = ca.hccis.util.CisUtility.getInputInt("Number of Days: ");

        setDestination(destination);
        setNumberOfTravelers(numberOfTravelers);
        setNumberOfDays(numberOfDays);
    }
    //Getters + Setters
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

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    @Override
    public String toString() {
        return String.format(
                "Trip: travelId=%d, destination='%s', numberOfTravelers='%s', numberOfDays='%s'",
                travelId, destination, numberOfTravelers, numberOfDays
        );
    }

}
