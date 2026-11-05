DROP DATABASE IF EXISTS cis2232_travel_budget_planner;
CREATE DATABASE cis2232_travel_budget_planner;
USE cis2232_travel_budget_planner;

DROP TABLE IF EXISTS Trip;
CREATE TABLE Trip
(
    id                 int            NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique id for the trip',
    travelId           int            NOT NULL UNIQUE COMMENT 'The travel id entered by the user (10000-99999)',
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

-- Initial sample data (totalCost calculated using TripBO formula)
INSERT INTO Trip
(id, travelId, destinationName, numberOfTravelers, numberOfDays,
 flightCost, hotelCostPerNight, foodCostPerDay, transportationCost,
 activitiesCost, travelInsurance, otherExpenses, totalCost)
VALUES (1, 10001, 'Paris, France', 2, 7, 950.00, 180.00, 60.00, 150.00, 400.00, true, 100.00, 6059.99),
       (2, 10002, 'Tokyo, Japan', 1, 10, 1950.00, 140.00, 50.00, 200.00, 600.00, true, 250.00, 5049.99),
       (3, 10003, 'Toronto, Canada', 4, 4, 550.00, 120.00, 45.00, 80.00, 300.00, false, 50.00, 5270.00),
       (4, 10004, 'Rome, Italy', 2, 6, 1350.00, 150.00, 55.00, 120.00, 350.00, true, 75.00, 5854.99),
       (5, 10005, 'Cairo, Egypt', 3, 8, 1450.00, 90.00, 35.00, 180.00, 500.00, false, 0.00, 8030.00),
       (6, 10006, 'Barcelona, Spain', 2, 5, 700.00, 130.00, 50.00, 100.00, 250.00, true, 80.00, 3779.99),
       (7, 10007, 'New York, USA', 1, 4, 400.00, 200.00, 70.00, 60.00, 150.00, false, 120.00, 1810.00),
       (8, 10008, 'Sydney, Australia', 3, 12, 2200.00, 170.00, 65.00, 250.00, 700.00, true, 300.00, 16459.99),
       (9, 10009, 'Mumbai, India', 2, 7, 800.00, 60.00, 30.00, 100.00, 200.00, false, 50.00, 3210.00),
       (10, 10010, 'Cape Town, South Africa', 4, 9, 1600.00, 110.00, 40.00, 200.00, 450.00, true, 150.00, 12749.99),
       (11, 10011, 'Marrakech, Morocco', 2, 3, 900.00, 70.00, 25.00, 50.00, 100.00, false, 30.00, 2550.00),
       (12, 10012, 'Bangkok, Thailand', 3, 6, 1100.00, 80.00, 35.00, 150.00, 350.00, true, 100.00, 6119.99),
       (13, 10013, 'Lisbon, Portugal', 1, 5, 600.00, 100.00, 45.00, 70.00, 180.00, false, 60.00, 1635.00),
       (14, 10014, 'Dubai, UAE', 2, 4, 1300.00, 250.00, 80.00, 100.00, 500.00, true, 200.00, 6189.99),
       (15, 10015, 'Amsterdam, Netherlands', 3, 6, 850.00, 140.00, 55.00, 120.00, 300.00, false, 90.00, 6570.00);
