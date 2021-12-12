import org.junit.jupiter.api.Test;
import ru.sber.DispatcherImpl;
import ru.sber.Taxi;
import ru.sber.TaxiImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaxiTest {
    @Test
    public void testOneThread() throws InterruptedException {
        Integer maxOrders = 10;
        Integer taxisAmount = 1;

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
        thread.join();
        assertEquals(maxOrders, taxis.get(0).getExecutedOrders().size());
    }

    @Test
    public void testMultiThread() throws InterruptedException {
        Integer maxOrders = 30;
        Integer taxisAmount = 4;

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
        thread.join();
        Integer completedOrders = 0;
        for (Taxi taxi : taxis) {
            completedOrders += taxi.getExecutedOrders().size();
        }

        assertEquals(maxOrders, completedOrders);
    }
}
