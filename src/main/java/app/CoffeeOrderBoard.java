package app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeOrderBoard {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);
    private final List<Order> orders = new ArrayList<>();
    private int lastNumber = 0;

    public void add(String name) {
        lastNumber++;
        Order order = new Order(lastNumber, name);
        orders.add(order);
        logger.info("Added order: {}", order);
    }

    public Order deliver() {
        if (orders.isEmpty()) {
            logger.warn("No orders to deliver");
            return null;
        }
        Order order = orders.remove(0);
        logger.info("Delivered order: {}", order);
        return order;
    }

    public Order deliver(int number) {
        Order order = orders.stream()
                .filter(o -> o.getNumber() == number)
                .findFirst()
                .orElse(null);
        if (order == null) {
            logger.warn("Order with number {} not found", number);
            return null;
        }
        orders.remove(order);
        logger.info("Delivered order: {}", order);
        return order;
    }

    public void draw() {
        logger.info("Current orders:");
        logger.info("Num | Name");
        for (Order order : orders) {
            logger.info("{} | {}", order.getNumber(), order.getName());
        }
    }
}
