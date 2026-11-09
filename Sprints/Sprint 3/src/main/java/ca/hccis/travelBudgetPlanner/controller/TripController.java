package ca.hccis.travelBudgetPlanner.controller;

import ca.hccis.travelBudgetPlanner.bo.TripBO;
import ca.hccis.travelBudgetPlanner.entity.Trip;
import ca.hccis.travelBudgetPlanner.repository.TripRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Trip CRUD operations (Create, Read, Update, Delete).
 * Uses Spring Data JPA for database access and Spring Validation for input validation.
 *
 * @author Peter Logan
 * @since 2026-11-05
 */
@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    // =========================================================================
    // LIST - Display all trips
    // =========================================================================

    /**
     * Displays the list of all trips.
     *
     * @param model Spring Model to pass data to the view
     * @return the name of the trips list view
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @GetMapping
    public String listTrips(Model model) {

        List<Trip> trips = tripRepository.findAll();

        for (Trip trip : trips) {
            System.out.println(
                    "ID=" + trip.getId() +
                            ", Travel ID=" + trip.getTravelId() +
                            ", Destination=" + trip.getDestinationName() +
                            ", Travelers=" + trip.getNumberOfTravelers() +
                            ", Days=" + trip.getNumberOfDays() +
                            ", Flight=" + trip.getFlightCost()
            );
        }

        model.addAttribute("trips", trips);

        return "trips/list";
    }

    // =========================================================================
    // ADD - Display form and process new trip
    // =========================================================================

    /**
     * Displays the add trip form.
     *
     * @param model Spring Model to pass data to the view
     * @return the name of the add trip view
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("isEdit", false);
        return "trips/tripForm";
    }

    /**
     * Processes the add trip form submission.
     * Validates input, calculates total cost, and saves to database.
     *
     * @param trip    the Trip object bound from form fields
     * @param result  the binding result containing validation errors
     * @param model   Spring Model to pass data to the view
     * @return the name of the view to display (redirect on success, form on error)
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @PostMapping("/add")
    public String addTrip(@Valid @ModelAttribute("trip") Trip trip,
                          BindingResult result,
                          Model model) {
        // Check for validation errors
        if (result.hasErrors()) {
            model.addAttribute("isEdit", false);
            return "trips/tripForm";
        }

        // Check for duplicate travel ID
        if (tripRepository.existsByTravelId(trip.getTravelId())) {
            model.addAttribute("duplicateTravelId", true);
            model.addAttribute("isEdit", false);
            return "trips/tripForm";
        }

        // Calculate total cost using TripBO
        double totalCost = TripBO.calculateTripCost(trip);
        trip.setTotalCost(totalCost);

        // Save to database
        tripRepository.save(trip);

        return "redirect:/trips";
    }

    // =========================================================================
    // UPDATE - Display edit form and process updates
    // =========================================================================

    /**
     * Displays the edit trip form pre-populated with existing data.
     *
     * @param id    the ID of the trip to edit
     * @param model Spring Model to pass data to the view
     * @return the name of the edit trip view, or redirect if not found
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip == null) {
            return "redirect:/trips";
        }
        model.addAttribute("trip", trip);
        model.addAttribute("isEdit", true);
        return "trips/tripForm";
    }

    /**
     * Processes the edit trip form submission.
     * Validates input, recalculates total cost, and updates in database.
     *
     * @param trip    the Trip object bound from form fields
     * @param result  the binding result containing validation errors
     * @param model   Spring Model to pass data to the view
     * @return the name of the view to display (redirect on success, form on error)
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @PostMapping("/edit")
    public String updateTrip(@Valid @ModelAttribute("trip") Trip trip,
                             BindingResult result,
                             Model model) {
        // Check for validation errors
        if (result.hasErrors()) {
            model.addAttribute("isEdit", true);
            return "trips/tripForm";
        }

        // Check if trip exists
        Trip existingTrip = tripRepository.findById(trip.getId()).orElse(null);
        if (existingTrip == null) {
            return "redirect:/trips";
        }

        // Check for duplicate travel ID (if changed)
        if (!existingTrip.getTravelId().equals(trip.getTravelId())
                && tripRepository.existsByTravelId(trip.getTravelId())) {
            model.addAttribute("duplicateTravelId", true);
            model.addAttribute("isEdit", true);
            return "trips/tripForm";
        }

        // Calculate total cost using TripBO
        double totalCost = TripBO.calculateTripCost(trip);
        trip.setTotalCost(totalCost);

        // Save to database
        tripRepository.save(trip);

        return "redirect:/trips";
    }

    // =========================================================================
    // DELETE - Delete a trip
    // =========================================================================

    /**
     * Deletes a trip by its ID.
     *
     * @param id the ID of the trip to delete
     * @return redirect to the trips list
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable Integer id) {
        tripRepository.deleteById(id);
        return "redirect:/trips";
    }
}
