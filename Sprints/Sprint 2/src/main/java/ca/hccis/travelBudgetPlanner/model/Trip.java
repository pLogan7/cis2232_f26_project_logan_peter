package ca.hccis.travelBudgetPlanner.model;

/**
 * Model class representing a Trip in the Travel Budget Planner application.
 * Maps to the Trip table in the database.
 *
 * @author Peter Logan
 * @since 2026-10-04
 */
public class Trip {

    private Integer id;
    private Integer travelId;
    private Integer destinationId;
    private String destinationName;
    private Integer numberOfTravelers;
    private Integer numberOfDays;
    private Double flightCost;
    private Double hotelCostPerNight;
    private Double foodCostPerDay;
    private Double transportationCost;
    private Double activitiesCost;
    private Boolean travelInsurance;
    private Double otherExpenses;
    private Double totalCost;

    // Constructors
    public Trip() {
        this.travelInsurance = false;
        this.otherExpenses = 0.0;
        this.totalCost = 0.0;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Integer getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(Integer numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Double getFlightCost() {
        return flightCost;
    }

    public void setFlightCost(Double flightCost) {
        this.flightCost = flightCost;
    }

    public Double getHotelCostPerNight() {
        return hotelCostPerNight;
    }

    public void setHotelCostPerNight(Double hotelCostPerNight) {
        this.hotelCostPerNight = hotelCostPerNight;
    }

    public Double getFoodCostPerDay() {
        return foodCostPerDay;
    }

    public void setFoodCostPerDay(Double foodCostPerDay) {
        this.foodCostPerDay = foodCostPerDay;
    }

    public Double getTransportationCost() {
        return transportationCost;
    }

    public void setTransportationCost(Double transportationCost) {
        this.transportationCost = transportationCost;
    }

    public Double getActivitiesCost() {
        return activitiesCost;
    }

    public void setActivitiesCost(Double activitiesCost) {
        this.activitiesCost = activitiesCost;
    }

    public Boolean getTravelInsurance() {
        return travelInsurance;
    }

    public void setTravelInsurance(Boolean travelInsurance) {
        this.travelInsurance = travelInsurance;
    }

    public Double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(Double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", travelId=" + travelId +
                ", destinationName='" + destinationName + '\'' +
                ", numberOfTravelers=" + numberOfTravelers +
                ", numberOfDays=" + numberOfDays +
                ", totalCost=" + totalCost +
                '}';
    }
}
