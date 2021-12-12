package ru.sber;


public class Order {
    private final Boolean isStopOrder;

    public Order(Boolean isStopOrder) {
        this.isStopOrder = isStopOrder;
    }

    public void run() {
        try {
            Thread.sleep((long) (1000 + Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Boolean isStop() {
        return isStopOrder;
    }

}
