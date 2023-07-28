package siberteam.testperiod.mt.subtask.fifth;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RequiredArgsConstructor
public class Car {
    private final CyclicBarrier barrier;

    public void waitForFerry() throws BrokenBarrierException, InterruptedException {
        System.out.println("Car (" + Thread.currentThread().getName() + ") came to jetty");
        barrier.await();
    }
}
