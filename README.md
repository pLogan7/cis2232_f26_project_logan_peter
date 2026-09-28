# cis2232_f26_project_logan_peter #
#Travel Budget Application
CIS2232 Advanced Object Oriented Programming Project

## Development Team ##
Business Client: Yasir Al Muhib
Lead Developer: Peter Logan
Quality Control: Bivan Fedha

## Description ##
This web application help people and small groups plan the costs of a trip. 
The Travel Budget Planner puts all expenses in one place, and helps users see estimated total costs before booking anything.
The app can be used by travel consultants or small travel planning businesses to help clients quickly estimate vacation costs.
It can also be used by individuals, families, or groups who want to plan and compare travel budgets on their own.

Users enter details about their trip, such as the destination, number of travelers, number of days, and expected expenses. The app then calculates the total estimated cost of the trip automatically. 
It also shows the estimated cost for each traveler, making it easier to know how much money each person should save or spend.
For example, a group can enter the cost of flights, hotels, food, transportation, activities, and other expenses. 
Travel Budget Planner will add all these costs together and provide a total trip estimate. It can also show a breakdown of expenses so users can see which parts of the trip cost the most money.

## Color ##
Teal

## Required Fields ##
id int (Unique Identifier for database table)
travelId int (Identifier for a trip)
destination String
numberOfTravelers int
numberOfDays int
flightCost double
hotelCostPerNight double
foodCostPerDay double
transportationCost double
activitiesCost double
travelInsurance double
otherExpenses double
totalCost double

## Calculation ##
The application will provide users with an estimated overall budget for their trip and an estimated cost per person.
The budget will take into account the different travel expenses entered by the user, allowing them to understand their expected spending and plan their trip accordingly.
The totalCost field is populated based on 
perPersonCost =
    flightCost
    + (numberOfDays × hotelCostPerNight)
    + (numberOfDays × foodCostPerDay)
    + activitiesCost
    + travelInsurance

totalCost =
    (perPersonCost × numberOfTravelers)
    + transportationCost
    + otherExpenses

## Report Details ##
TBD
