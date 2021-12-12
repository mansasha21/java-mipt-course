package ru.sber;


import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;


public class DispatcherImpl implements Dispatcher {

    private Queue<Order> orders = new ArrayDeque<>();
    private Queue<Taxi> freeTaxis = new ArrayDeque<>();
    private final Integer maxOrders;
    private Integer taxisAmount = 0;

    public DispatcherImpl(Integer maxOrders) {
        this.maxOrders = maxOrders;
    }

    public void addTaxis(List<Taxi> taxis) {
        for (Taxi taxi : taxis) {
            freeTaxis.add(taxi);
        }
        synchronized (this) {
            taxisAmount += taxis.size();
        }
    }

    @Override
    public void notifyAvailable(Taxi taxi) {
        synchronized (freeTaxis) {
            freeTaxis.add(taxi);
            freeTaxis.notify();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < maxOrders; i++) {
            orders.add(new Order(false));
        }
        while (!orders.isEmpty()) {
            Taxi taxi = pollQueue(freeTaxis);
            Order order = pollQueue(orders);
            taxi.placeOrder(order);
        }
        waitOtherTaxis();
        stop();
    }

    private <E> E pollQueue(Queue<E> queue) {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.poll();
        }
    }

    void waitOtherTaxis() {
        synchronized (freeTaxis) {
            while (freeTaxis.size() != taxisAmount) {
                try {
                    freeTaxis.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void stop() {
        for (Taxi freeTaxi : freeTaxis) {
            freeTaxi.placeOrder(new Order(true));
        }
    }
}
