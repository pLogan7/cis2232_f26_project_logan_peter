/**
 * CIS2232 Assignment 1
 * Travel Budget Planner
 *
 * @author Peter Logan
 * @BA/BusinessClient Yasir Al Muhib
 * @ProjectManager/QA Bivan Fedha
 * @since 09/21/2026
 */

package ca.hccis.files;

import ca.hccis.files.entity.Trip;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * Controls the overall flow of the program.
 *
 * @author cis2232
 * @since 20260917
 */
public class Controller {

    public static final String EXIT = "X";

    public static final String MENU = "A) Add" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + EXIT + ") Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    private static HashMap<Integer, Trip> tripHashMap = new HashMap();
    private static Gson gson = new Gson();

    public static final String PATH_NAME = "c:\\cis2232\\data_Logan_Peter.json";

    public static void main(String[] args) {

        initialize();

        //Gson
//        Camper test = camperMap.get(22334);
//        String camperJson = gson.toJson(test);
//        IO.println(camperJson);
//
//        Camper camperFromJson = gson.fromJson(camperJson, Camper.class);
//        System.out.println(camperFromJson.toString());


        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU).toUpperCase();

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break; //Break out of the loop as we're finished.
                case "A":
                    add();
                    break;
                case "V":
                    viewAll();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equalsIgnoreCase(EXIT));
    }

    /**
     * Processing for menu option A (Add)
     *2
     * @author Peter Logan
     * @since September 26 2026
     */
    public static void add() {
        Trip newTrip = new Trip();
        IO.println("--Add Trip--");
        newTrip.getInformation();

        if (tripHashMap.containsKey(newTrip.getTravelId())) {

            boolean answer = CisUtility.getInputBoolean(
                    "Travel ID already exists. Overwrite? (Y/N): "
            );

            if (!answer) {
                System.out.println("Trip was not added.");
                return;
            }
        }

        tripHashMap.put(newTrip.getTravelId(), newTrip);
        writeAll();
    }

    /**
     * Processing for menu option 2.
     *
     * @author
     * @since
     */
//    public static void edit() {
//        System.out.println("Processing option 2");
//        int regID = CisUtility.getInputInt("Reg ID: ");
//        Trip editingTrip = tripHashMap.get(regID);
//        editingTrip.edit();
//        //TODO What if the regID not found?
//        //Handle this situation.
//        writeAll(); //save to file
//    }

    /**
     * Processing for menu (V) View All
     *
     * @author Peter Logan
     * @since September 26 2026
     */
    public static void viewAll() {

        // Read the latest information from the file first.
        readAll();

        System.out.println("--All Trips--");
        if (tripHashMap.isEmpty()) {
            System.out.println("No trips found.");
            return;
        }

        for (Trip current : tripHashMap.values()) {
            System.out.println(current);
        }
    }


    public static void writeAll() {
        try (FileWriter writer = new FileWriter(PATH_NAME, false)) {

            for (Trip current : tripHashMap.values()) {

                writer.append(gson.toJson(current));
                writer.append(System.lineSeparator());
            }
            System.out.println("Successfully written JSON string to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAll() {

        // BufferedReader allows us to read the file one line at a time.
        try (BufferedReader reader = new BufferedReader(
                new FileReader(PATH_NAME))) {

            String line;

            // Read each line until there are no more lines.
            while ((line = reader.readLine()) != null) {

                // Ignore empty lines.
                if (!line.trim().isEmpty()) {

                    Trip tripFromJson = gson.fromJson(line, Trip.class);

                    tripHashMap.put(
                            tripFromJson.getTravelId(),
                            tripFromJson
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {

        Path path = Paths.get(PATH_NAME);

        // *** ADDED ***
        // Get the directory containing the JSON file.
        Path directory = path.getParent();

        // *** ADDED ***
        // Create C:\cis2232 if it doesn't already exist.
        try {
            Files.createDirectories(directory);
        } catch (IOException e) {
            System.out.println("Unable to create directory.");
            e.printStackTrace();
            return;
        }

        // Check if the file exists
        if (Files.exists(path)) {

            System.out.println("Trips exist.");

            readAll();

        } else {

            Trip trip = new Trip(1, 10123, "Miami", 2, 4);
            Trip trip2 = new Trip(2, 10145, "Toronto", 1, 4);
            Trip trip3 = new Trip(3, 12489, "Sydney", 3, 3);
            Trip trip4 = new Trip(4, 31772, "New York City", 5, 5);
            Trip trip5 = new Trip(5, 24782, "Charlottetown", 2, 10);

            tripHashMap.put(trip.getTravelId(), trip);
            tripHashMap.put(trip2.getTravelId(), trip2);
            tripHashMap.put(trip3.getTravelId(), trip3);
            tripHashMap.put(trip4.getTravelId(), trip4);
            tripHashMap.put(trip5.getTravelId(), trip5);

            writeAll();
        }
    }
}
