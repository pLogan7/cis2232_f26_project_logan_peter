/**
 * CIS2232 Assignment 1
 * Travel Budget Planner
 *
 * @author Peter Logan
 * @BA/BusinessClient Yasir Al Muhib
 * @ProjectManager/QA Bivan Fedha
 *
 * @since 09/21/2026
 */

package ca.hccis.files;

import ca.hccis.files.entity.Trip;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;

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

    public static final int EXIT = 0;

    public static final String MENU = "1) Add" + System.lineSeparator()
            + "2) Edit" + System.lineSeparator()
            + "3) View" + System.lineSeparator()
            + EXIT + ") Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    private static HashMap<Integer, Trip> camperMap = new HashMap();
    private static Gson gson = new Gson();

    //TODO if the cis2232 folder does not exist, then have your program create it.
    //TODO filename to be changed from campers based on assignment requirements.
    public static final String PATH_NAME = "c:\\cis2232\\campers.json";

    public static void main(String[] args) {

        initialize();

        //Gson
//        Camper test = camperMap.get(22334);
//        String camperJson = gson.toJson(test);
//        IO.println(camperJson);
//
//        Camper camperFromJson = gson.fromJson(camperJson, Camper.class);
//        System.out.println(camperFromJson.toString());


        int menuOption;

        do {
            menuOption = CisUtility.getInputInt(MENU);

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break; //Break out of the loop as we're finished.
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    viewAll();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (menuOption != EXIT);
    }

    /**
     * Processing for menu option 1
     *
     * @author
     * @since
     */
    public static void add() {
        Trip newTrip = new Trip();
        IO.println("--Add Camper--");
        newTrip.getInformation();

        //TODO what if the registration id already exists.  Give the user a warning and ask if they want to overwrite
        //the row.
        //read file nad see if the new camper is already there, and if so check with user to see if should overwrite
        camperMap.put(newTrip.getRegistrationId(), newTrip);
        writeAll();
    }

    /**
     * Processing for menu option 2.
     *
     * @author
     * @since
     */
    public static void edit() {
        System.out.println("Processing option 2");
        int regID = CisUtility.getInputInt("Reg ID: ");
        Trip editingTrip = camperMap.get(regID);
        editingTrip.edit();
        //TODO What if the regID not found?
        //Handle this situation.
        writeAll(); //save to file
    }

    /**
     * Processing for menu option 3.
     *
     * @author
     * @since
     */
    public static void viewAll() {
        readAll();
        //TODO Need to show all the campers.  Note want to show the latest from the file, not just
        //what is currently in the map.
    }


    public static void writeAll() {
        try {
            FileWriter writer = new FileWriter(PATH_NAME, false);
            for (Trip current : camperMap.values()) {
                writer.append(gson.toJson(current));
                writer.append(System.lineSeparator());
                System.out.println("Successfully written JSON string to file.");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAll() {
        try {
            FileReader reader = new FileReader(PATH_NAME);
            List<String> lines = reader.readAllLines();
            for(int i = 0; i < lines.size(); i++) {
                Trip tripFromJson = gson.fromJson(lines.get(i), Trip.class);
                camperMap.put(tripFromJson.getRegistrationId(), tripFromJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initialize() {

        Path path = Paths.get(PATH_NAME);

        // Check if the file exists
        if (Files.exists(path)) {
            System.out.println("Campers exist.");
            readAll();
        } else {


            Trip trip = new Trip(1, 22334, "Bob", "Stephens", "2020-01-05");
            Trip trip2 = new Trip(2, 22335, "Alice", "Johnson", "2019-07-14");
            Trip trip3 = new Trip(3, 22336, "Charlie", "Williams", "2021-03-22");
            Trip trip4 = new Trip(4, 22337, "Diana", "Brown", "2020-11-09");
            Trip trip5 = new Trip(5, 22338, "Ethan", "Miller", "2018-05-17");
            camperMap.put(trip.getRegistrationId(), trip);
            camperMap.put(trip2.getRegistrationId(), trip2);
            camperMap.put(trip3.getRegistrationId(), trip3);
            camperMap.put(trip4.getRegistrationId(), trip4);
            camperMap.put(trip5.getRegistrationId(), trip5);

            writeAll();
        }

    }
}
