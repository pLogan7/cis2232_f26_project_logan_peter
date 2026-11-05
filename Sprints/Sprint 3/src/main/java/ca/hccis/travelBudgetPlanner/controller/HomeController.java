package ca.hccis.travelBudgetPlanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the home page of the Travel Budget Planner application.
 *
 * @author Peter Logan
 * @since 2026-11-05
 */
@Controller
public class HomeController {

    /**
     * Maps the root URL to the home page.
     *
     * @return the name of the Thymeleaf template for the home page
     *
     * @author Peter Logan
     * @since 2026-11-05
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
