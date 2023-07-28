package siberteam.testperiod.mt.usbtask.fifth;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ferry {
    private final CyclicBarrier barrier;
    private final ExecutorService carryExecutor;

    public Ferry(CyclicBarrier barrier) {
        this.barrier = barrier;
        carryExecutor = Executors.newSingleThreadExecutor();
    }

    public void stopCarrying() {
        carryExecutor.shutdown();
    }

    public void carry() {
        carryExecutor.submit(this::carryIteration);
    }

    private void carryIteration() {
        System.out.println("3 cars carried");
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            System.err.println("Exception occurred on ferry station. Message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
