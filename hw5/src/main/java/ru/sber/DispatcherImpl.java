package ru.sber;


import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;


public class DispatcherImpl implements Dispatcher {

    private Queue<Order> orders;
    private Queue<Taxi> freeTaxis;
    private volatile Integer completedOrders;
    private final Integer maxOrders;

    public DispatcherImpl(Integer maxOrders) {
        this.maxOrders = maxOrders;
        orders = new ConcurrentLinkedDeque<>();
        freeTaxis = new ConcurrentLinkedDeque<>();
        completedOrders = 0;

    }

    public void addTaxis(List<Taxi> taxis) {
        for (Taxi taxi : taxis) {
            freeTaxis.add(taxi);
        }
    }

    @Override
    public void notifyAvailable(Taxi taxi) {
        completedOrders += 1;
        freeTaxis.add(taxi);
        //System.out.println("Order " + completedOrders + " completed");
    }

    @Override
    public void run() {
        for (int i = 0; i < maxOrders; i++) {
            orders.add(new Order());
        }
        while (completedOrders < maxOrders) {
            if (freeTaxis.isEmpty() || orders.isEmpty()) {
                continue;
            }
            freeTaxis.remove().placeOrder(orders.remove());
        }
    }
}
