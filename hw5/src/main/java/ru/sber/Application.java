package ru.sber;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Integer maxOrders = 100;
        Integer taxisAmount = 10;

        var dispatcher = new DispatcherImpl(maxOrders);
        List<Taxi> taxis = new ArrayList<Taxi>(taxisAmount);

        for (int i = 0; i < taxisAmount; i++) {
            var taxi = new TaxiImpl(dispatcher);
            taxis.add(taxi);
            Thread thread = new Thread(taxi);
            thread.start();
        }

        dispatcher.addTaxis(taxis);
        Thread thread = new Thread(dispatcher);
        thread.start();
    }
}
