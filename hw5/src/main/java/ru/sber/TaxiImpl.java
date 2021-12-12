package ru.sber;

import java.util.ArrayList;
import java.util.List;


public class TaxiImpl implements Taxi {
    private final DispatcherImpl dispatcher;
    private volatile Order curOrder = null;
    private final List<Order> executedOrders = new ArrayList<>();

    public TaxiImpl(DispatcherImpl dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {

        while (true) {
            waitOrder();
            if (curOrder.isStop()) {
                break;
            }
            runOrder();
            dispatcher.notifyAvailable(this);
        }
    }

    @Override
    public synchronized void placeOrder(Order order) {
        curOrder = order;
        this.notify();
    }

    @Override
    public List<Order> getExecutedOrders() {
        synchronized (executedOrders) {
            return new ArrayList<>(executedOrders);
        }
    }

    private void runOrder() {
        curOrder.run();
        synchronized (executedOrders) {
            executedOrders.add(curOrder);
        }
        curOrder = null;
    }

    private synchronized void waitOrder() {
        while (curOrder == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
