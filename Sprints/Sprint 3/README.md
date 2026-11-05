# Travel Budget Planner

## Sprint 3 – MVC CRUD with Spring Data JPA and Validation

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
| destinationName | String | The city or country the user plans to visit (max 30 chars). |
| numberOfTravelers | Integer | The total number of people going on the trip (1-100). |
| numberOfDays | Integer | The total number of days of the trip (1-365). |
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

Displays every trip in the database with summary statistics (total trips, total combined cost, average cost per trip).

### Filter by Destination

**Inputs:**
- Filter by destination (dropdown populated with distinct destinations from the database)

**Expected Output:**
- Table displaying all matching trips with: ID, Destination, Travelers, Days, Total Cost, Cost Per Person
- Summary statistics: Total number of trips, Average cost per trip, Total combined cost
- Report exported to file in `c:\cis2232\` folder with filename format: `TripSummaryReportyyyyMMddHHmmss.txt`

### Filter by Travelers

**Inputs:**
- Filter by number of travelers (dropdown populated with distinct traveler counts from the database)

**Expected Output:**
- Table displaying all matching trips with: ID, Destination, Travelers, Days, Total Cost, Cost Per Person
- Summary statistics: Total number of trips, Average cost per trip, Total combined cost
- Report exported to file in `c:\cis2232\` folder with filename format: `TripSummaryReportyyyyMMddHHmmss.txt`

## Sprint 3 Features

- **JPA Entity**: `Trip` entity with JSR-303 validation annotations (`@NotNull`, `@Min`, `@Max`, `@NotBlank`, `@Size`, `@DecimalMin`, `@DecimalMax`)
- **Spring Data JPA Repository**: `TripRepository` with custom query methods for searching and duplicate detection
- **MVC Controllers**: `HomeController` and `TripController` for full CRUD operations (Create, Read, Update, Delete)
- **Business Object**: `TripBO` calculates total trip cost with null-safety
- **Thymeleaf Templates**: Home page, trip list, add/edit form, and report pages with validation error display
- **Duplicate Travel ID Check**: Prevents adding or updating trips with duplicate travel IDs
- **Report Functionality**: Three report types — View All Trips, Filter by Destination, and Filter by Travelers — with JDBC-based report generation and file I/O export

## Technology Stack

- **Backend:** Java 21, Spring Boot 3.2.5
- **Database:** MySQL 8.0 via XAMPP
- **Templating:** Thymeleaf
- **Validation:** Spring Boot Validation (JSR-303 / Bean Validation)
- **ORM:** Spring Data JPA with Hibernate
- **Build Tool:** Maven

## Setup Instructions

1. Start MySQL via XAMPP
2. Run `src/main/resources/db/mysql/createDatabase.sql` in MySQL
3. Update `application.properties` with your MySQL credentials if needed
4. Run the application: `mvn spring-boot:run`
5. Open `http://localhost:8080` in your browser
