package ca.hccis.travelBudgetPlanner.controllers;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffeeorder")
public class CoffeeOrderController {

    private final CoffeeOrderRepository _cor;

    public CoffeeOrderController(CoffeeOrderRepository cor) {
        _cor = cor;
    }

    @RequestMapping("")
    public String home(Model model) {
        Iterable<CoffeeOrder> orders = _cor.findAll();
        model.addAttribute("orders", orders);
        return "index"; // Or "coffeeorder/list" if you have a subfolder template
    }
}