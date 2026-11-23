package ca.hccis.travelBudgetPlanner.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * JPA Entity representing a Trip in the Travel Budget Planner application.
 * Includes validation annotations for add and update operations.
 *
 * @author Peter Logan
 * @since 2026-11-05
 */
@Entity
@Table(name = "Trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "travelId")
    @NotNull(message = "Travel ID is required")
    @Min(value = 10000, message = "Travel ID must be at least 10000")
    @Max(value = 99999, message = "Travel ID must be at most 99999")
    private Integer travelId;

    @Column(name = "destinationName", length = 30)
    @NotNull(message = "Destination name is required")
    @NotBlank(message = "Destination name cannot be blank")
    @Size(min = 1, max = 30, message = "Destination name must be between 1 and 30 characters")
    private String destinationName;

    @Column(name = "numberOfTravelers")
    @NotNull(message = "Number of travelers is required")
    @Min(value = 1, message = "At least 1 traveler is required")
    @Max(value = 100, message = "Maximum 100 travelers allowed")
    private Integer numberOfTravelers;

    @Column(name = "numberOfDays")
    @NotNull(message = "Number of days is required")
    @Min(value = 1, message = "At least 1 day is required")
    @Max(value = 365, message = "Maximum 365 days allowed")
    private Integer numberOfDays;

    @Column(name = "flightCost")
    @NotNull(message = "Flight cost is required")
    @DecimalMin(value = "0.00", message = "Flight cost cannot be negative")
    @DecimalMax(value = "999999.99", message = "Flight cost is too large")
    private Double flightCost;

    @Column(name = "hotelCostPerNight")
    @NotNull(message = "Hotel cost per night is required")
    @DecimalMin(value = "0.00", message = "Hotel cost cannot be negative")
    @DecimalMax(value = "999999.99", message = "Hotel cost is too large")
    private Double hotelCostPerNight;

    @Column(name = "foodCostPerDay")
    @NotNull(message = "Food cost per day is required")
    @DecimalMin(value = "0.00", message = "Food cost cannot be negative")
    @DecimalMax(value = "999999.99", message = "Food cost is too large")
    private Double foodCostPerDay;

    @Column(name = "transportationCost")
    @NotNull(message = "Transportation cost is required")
    @DecimalMin(value = "0.00", message = "Transportation cost cannot be negative")
    @DecimalMax(value = "999999.99", message = "Transportation cost is too large")
    private Double transportationCost;

    @Column(name = "activitiesCost")
    @NotNull(message = "Activities cost is required")
    @DecimalMin(value = "0.00", message = "Activities cost cannot be negative")
    @DecimalMax(value = "999999.99", message = "Activities cost is too large")
    private Double activitiesCost;

    @Column(name = "travelInsurance")
    private Boolean travelInsurance = false;

    @Column(name = "otherExpenses")
    @NotNull(message = "Other expenses is required")
    @DecimalMin(value = "0.00", message = "Other expenses cannot be negative")
    @DecimalMax(value = "999999.99", message = "Other expenses is too large")
    private Double otherExpenses;

    @Column(name = "totalCost")
    private Double totalCost;

    // Constructors
    public Trip() {
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
                ", destinationName='" + destinationName + '\'' +
                ", numberOfTravelers=" + numberOfTravelers +
                ", numberOfDays=" + numberOfDays +
                ", totalCost=" + totalCost +
                '}';
    }
}
