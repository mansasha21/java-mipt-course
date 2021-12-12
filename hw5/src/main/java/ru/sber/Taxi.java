package ru.sber;

import java.util.List;

public interface Taxi extends Runnable {
    void run();

    void placeOrder(Order order);

    List<Order> getExecutedOrders();
}
