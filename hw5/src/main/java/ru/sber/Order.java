package ru.sber;


public class Order {
    public void run() {
        try {
            Thread.sleep((long) (1000 + Math.random() * 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
