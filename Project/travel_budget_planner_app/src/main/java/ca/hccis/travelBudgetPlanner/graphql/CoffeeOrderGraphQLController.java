package ca.hccis.travelBudgetPlanner.graphql;

import ca.hccis.coffee.jpa.entity.CoffeeOrder;
import ca.hccis.coffee.repository.CoffeeOrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CoffeeOrderGraphQLController {

    private final CoffeeOrderRepository repository;

    public CoffeeOrderGraphQLController(CoffeeOrderRepository repository) {
        this.repository = repository;
    }

    // Handles query: allOrders
    @QueryMapping
    public List<CoffeeOrder> allOrders() {
        return repository.findAll();
    }

    // Handles query: orderById(id: ID!)
    @QueryMapping
    public CoffeeOrder orderById(@Argument Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Handles mutation: createOrder(input: CoffeeOrderTechInput!)
    @MutationMapping
    public CoffeeOrder createOrder(@Argument("input") CoffeeOrderTechInput input) {
        CoffeeOrder order = new CoffeeOrder();
        order.setCustomerName(input.getCustomerName());
        order.setDrinkType(input.getDrinkType());
        order.setDrinkSize(input.getDrinkSize());
        order.setMilkType(input.getMilkType());
        order.setQuantity(input.getQuantity());
        order.setUnitPrice(input.getUnitPrice());
        order.setExtraShots(input.getExtraShots());
        order.setMilkSurcharge(input.getMilkSurcharge());
        order.setOrderStatus(input.getOrderStatus() != null ? input.getOrderStatus() : "Pending");

        return repository.save(order);
    }
}