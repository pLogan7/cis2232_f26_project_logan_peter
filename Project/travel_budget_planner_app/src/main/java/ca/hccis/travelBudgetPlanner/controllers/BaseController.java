package ca.hccis.travelBudgetPlanner.controllers;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import ca.hccis.coffee.util.CisUtility;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class BaseController {

    private final CoffeeOrderRepository _cor;

    @Autowired
    public BaseController(CoffeeOrderRepository cor) {
        _cor = cor;
    }

    @RequestMapping("/")
    public String home(HttpSession session, Model model) {
        String currentDate = CisUtility.getTodayString("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        ArrayList<CoffeeOrder> orders = new ArrayList<>();
        _cor.findAll().forEach(orders::add);
        model.addAttribute("orders", orders); //populates attribs in JS index page

        return "index";
    }
}
