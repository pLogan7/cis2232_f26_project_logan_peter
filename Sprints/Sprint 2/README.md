# Travel Budget Planner

## Sprint 2 – Report File I/O, JDBC, and Trip Management

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

## Fields

| Name | Data Type | Description |
|------|-----------|-------------|
| travelId | Integer | Unique travel identifier (10000-99999). |
| destinationId | Integer | Index of the destination in the destinations list. |
| destinationName | String | The city or country the user plans to visit (max 30 chars). |
| numberOfTravelers | Integer | The total number of people going on the trip. |
| numberOfDays | Integer | The total number of days of the trip. |
| flightCost | Double | The estimated cost of flights. |
| hotelCostPerNight | Double | The estimated cost of the hotel per person per night. |
| foodCostPerDay | Double | The estimated food cost per traveler per day. |
| transportationCost | Double | The estimated total cost of transportation during the trip. |
| activitiesCost | Double | The estimated total cost of activities. |
| travelInsurance | Boolean | Whether travel insurance was purchased for the trip. |
| otherExpenses | Double | Any other miscellaneous expenses. |
| totalCost | Double | The calculated total cost of the trip. |

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

### View All Trips

Displays all trips in the database with summary statistics including total trips, total combined cost, and average cost per trip.

### Trip Summary Report (Filtered by Destination)

**Inputs:**
- Destination dropdown: Select from destinations currently in the database

**Expected Output:**
- Table displaying all matching trips with: ID, Destination, Travelers, Days, Total Cost, Cost Per Person
- Summary statistics: Total number of trips, Average cost per trip, Total combined cost
- Report exported to file in `c:\cis2232\` folder with filename format: `TripSummaryReportyyyyMMddHHmmss.txt`

## Sprint 2 Features

- **Trip Management**: Add, view, edit, and delete trips using JDBC DAO
- **JDBC DAO**: `TripDAO` and `TripReportDAO` use raw JDBC for all database operations
- **Business Object**: `TripBO` calculates total trip cost based on all entered variables
- **Trip Controller**: `TripController` handles CRUD operations for trips
- **Report Controller**: `ReportController` handles report generation and file export
- **View All Trips**: Dedicated page showing all trips with summary statistics
- **Filtered Reports**: Destination dropdown populated from database for filtered reports
- **File I/O**: Report results are automatically written to a timestamped file in `c:\cis2232\`
- **Duplicate Prevention**: Travel ID uniqueness check when adding or editing trips

## Technology Stack

- **Backend:** Java 21, Spring Boot 3.2.5
- **Database:** MySQL 8.0 via XAMPP
- **Templating:** Thymeleaf
- **Data Access:** JDBC (raw JDBC, no JPA)
- **Build Tool:** Maven

## Setup Instructions

1. Start MySQL via XAMPP
2. Run `src/main/resources/db/mysql/createDatabase.sql` in MySQL
3. Update `application.properties` with your MySQL credentials if needed
4. Run the application: `mvn spring-boot:run`
5. Open `http://localhost:8080` in your browser
