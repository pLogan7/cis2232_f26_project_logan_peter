# Travel Budget Planner

## Project Information

| Role | Name |
|------|------|
| BA / Business Client | Yasir Al Muhib |
| Developer | Peter Logan |
| Project Manager / QA | Bivan Fedha |

## Project Title

**Travel Budget Planner**

## Project Base Color

**Teal** (#008080)

## Description

Travel Budget Planner is a web application that helps people and small groups plan the cost of a trip. Planning a vacation can be hard because there are many expenses to think about, such as flights, hotels, food, transportation, activities, and other extra costs. Travel Budget Planner makes it easier by putting all these expenses in one place and helping users see the estimated total cost before booking anything.

This app can be used by travel consultants or small travel planning businesses to help clients quickly estimate vacation costs. It can also be used by individuals, families, or groups who want to plan and compare travel budgets on their own.

Users enter details about their trip, such as the destination, number of travelers, number of days, and expected expenses. The app then calculates the total estimated cost of the trip automatically. It also shows the estimated cost for each traveler, making it easier to know how much money each person should save or spend.

## Fields

| Name | Data Type | Description |
|------|-----------|-------------|
| destination | String | The city or country the user plans to visit. |
| numberOfTravelers | int | The total number of people going on the trip. |
| numberOfDays | int | The total number of days of the trip. |
| flightCost | double | The estimated total cost of flights for all travelers. |
| hotelCostPerNight | double | The estimated cost of the hotel for one night. |
| foodCostPerDay | double | The estimated food cost per traveler for one day. |
| transportationCost | double | The estimated total cost of transportation during the trip. |
| activitiesCost | double | The estimated total cost of activities. |
| travelInsurance | boolean | Whether travel insurance was purchased for the trip. |
| otherExpenses | double | Any other miscellaneous expenses. |

## Calculation

The application provides users with an estimated overall budget for their trip and an estimated cost per person. The budget calculation takes into account:

- **Flight Cost** = flightCost × numberOfTravelers
- **Hotel Cost** = hotelCostPerNight × numberOfDays × numberOfTravelers
- **Food Cost** = foodCostPerDay × numberOfTravelers × numberOfDays
- **Transportation Cost** = transportationCost (total)
- **Activities Cost** = activitiesCost (total)
- **Travel Insurance** = $149.99 flat rate if purchased
- **Other Expenses** = otherExpenses (total)
- **Total Cost** = sum of all above

## Report Requirements

### Trip Summary Report

**Inputs:**
- Optional: Filter by destination (string)
- Optional: Filter by minimum number of travelers (int)
- Optional: Filter by maximum number of travelers (int)

**Expected Output:**
- Table displaying all matching trips with: ID, Destination, Travelers, Days, Total Cost, Cost Per Person
- Summary statistics: Total number of trips, Average cost per trip, Total combined cost
- Report exported to file in `c:\cis2232\` folder with filename format: `TripSummaryReportyyyyMMddHHmmss.txt`

## Technology Stack

- **Backend:** Java 21, Spring Boot 3.2.5
- **Database:** MySQL 8.0 via XAMPP
- **ORM:** Spring Data JPA (Hibernate)
- **Templating:** Thymeleaf
- **Build Tool:** Maven

## Setup Instructions

1. Start MySQL via XAMPP
2. Run `src/main/resources/db/mysql/createDatabase.sql` in MySQL
3. Update `application.properties` with your MySQL credentials if needed
4. Run the application: `mvn spring-boot:run`
5. Open `http://localhost:8080` in your browser
