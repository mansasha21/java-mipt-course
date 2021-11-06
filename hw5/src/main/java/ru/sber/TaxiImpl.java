package ru.sber;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class TaxiImpl implements Taxi {
    private final Dispatcher dispatcher;
    private volatile Order curOrder;
    private ArrayList<Order> executedOrders;

    public TaxiImpl(Dispatcher dispatcher) {
        curOrder = null;
        executedOrders = new ArrayList<>();
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {

        Instant startMoment = Instant.now();
        Instant curMoment = startMoment;

        while (Duration.between(startMoment, curMoment).toSeconds() < 30) {
            curMoment = Instant.now();
            if (curOrder == null) {
                continue;
            }
            curOrder.run();
            executedOrders.add(curOrder);
            curOrder = null;
            dispatcher.notifyAvailable(this);
        }
    }

    @Override
    public synchronized void placeOrder(Order order) {
        curOrder = order;
    }

    @Override
    public List<Order> getExecutedOrders() {
        return executedOrders;
    }
}
