DROP DATABASE IF EXISTS cis2232_travel_budget_planner;
CREATE DATABASE cis2232_travel_budget_planner;
USE cis2232_travel_budget_planner;

DROP TABLE IF EXISTS Trip;
CREATE TABLE Trip
(
    id                 int            NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique id for the trip',
    travelId           int            NOT NULL UNIQUE COMMENT 'The travel id entered by the user (10000-99999)',
    destinationId      int            NOT NULL COMMENT 'Index of the destination in the destinations list',
    destinationName    varchar(30)    NOT NULL COMMENT 'The name of the destination',
    numberOfTravelers  int            NOT NULL COMMENT 'How many people are traveling',
    numberOfDays       int            NOT NULL COMMENT 'How many days the trip lasts',
    flightCost         decimal(10, 2) NOT NULL COMMENT 'Flight cost based on the destination',
    hotelCostPerNight  decimal(10, 2) NOT NULL COMMENT 'Hotel cost per person, per night',
    foodCostPerDay     decimal(10, 2) NOT NULL COMMENT 'Food cost per person, per day',
    transportationCost decimal(10, 2) NOT NULL COMMENT 'Total other transportation cost',
    activitiesCost     decimal(10, 2) NOT NULL COMMENT 'Total activities cost',
    travelInsurance    boolean        NOT NULL DEFAULT FALSE COMMENT 'Whether travel insurance was purchased',
    otherExpenses      decimal(10, 2) NOT NULL DEFAULT 0 COMMENT 'Any other expenses',
    totalCost          decimal(10, 2) NOT NULL DEFAULT 0 COMMENT 'The calculated total cost of the trip'
) COMMENT 'This table stores details about each planned trip.';

-- Initial sample data
INSERT INTO Trip
(id, travelId, destinationId, destinationName, numberOfTravelers, numberOfDays,
 flightCost, hotelCostPerNight, foodCostPerDay, transportationCost,
 activitiesCost, travelInsurance, otherExpenses)
VALUES (1, 10001, 0, 'Paris, France', 2, 7, 950.00, 180.00, 60.00, 150.00, 400.00, true, 100.00),
       (2, 10002, 1, 'Tokyo, Japan', 1, 10, 1950.00, 140.00, 50.00, 200.00, 600.00, true, 250.00),
       (3, 10003, 5, 'Toronto, Canada', 4, 4, 550.00, 120.00, 45.00, 80.00, 300.00, false, 50.00),
       (4, 10004, 3, 'Rome, Italy', 2, 6, 1350.00, 150.00, 55.00, 120.00, 350.00, true, 75.00),
       (5, 10005, 8, 'Cairo, Egypt', 3, 8, 1450.00, 90.00, 35.00, 180.00, 500.00, false, 0.00);
