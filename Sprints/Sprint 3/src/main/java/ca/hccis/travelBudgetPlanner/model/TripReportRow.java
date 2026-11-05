package ca.hccis.travelBudgetPlanner.model;

/**
 * Data Transfer Object representing a single trip row from the Trip Summary Report.
 * Used to pass report data from the DAO to the Controller and View.
 *
 * @author Peter Logan
 * @since 2026-11-05
 */
public class TripReportRow {

    private int id;
    private String destinationName;
    private int numberOfTravelers;
    private int numberOfDays;
    private double totalCost;
    private double costPerPerson;

    // Constructors
    public TripReportRow() {
    }

    public TripReportRow(int id, String destinationName, int numberOfTravelers,
                         int numberOfDays, double totalCost, double costPerPerson) {
        this.id = id;
        this.destinationName = destinationName;
        this.numberOfTravelers = numberOfTravelers;
        this.numberOfDays = numberOfDays;
        this.totalCost = totalCost;
        this.costPerPerson = costPerPerson;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getCostPerPerson() {
        return costPerPerson;
    }

    public void setCostPerPerson(double costPerPerson) {
        this.costPerPerson = costPerPerson;
    }

    @Override
    public String toString() {
        return String.format(
                "TripReportRow{id=%d, destination='%s', travelers=%d, days=%d, totalCost=%.2f, costPerPerson=%.2f}",
                id, destinationName, numberOfTravelers, numberOfDays, totalCost, costPerPerson
        );
    }
}
