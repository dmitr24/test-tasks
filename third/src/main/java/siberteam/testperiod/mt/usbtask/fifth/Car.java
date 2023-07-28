package siberteam.testperiod.mt.usbtask.fifth;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RequiredArgsConstructor
public class Car {
    private final CyclicBarrier barrier;

    public void waitForFerry() {
        try {
            System.out.println("Car (" + Thread.currentThread().getName() + ") came to jetty");
            barrier.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            System.err.println("Error occurred while waiting for ferry. Message: " + e.getMessage());
            System.exit(1);
        }
    }
}
