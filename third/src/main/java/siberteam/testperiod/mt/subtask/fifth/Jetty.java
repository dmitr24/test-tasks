package siberteam.testperiod.mt.subtask.fifth;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Jetty {
    private final CyclicBarrier barrier;
    private final Ferry ferry;
    private final ExecutorService carsAdder;
    private final int executionTime;

    public Jetty(int carsPerIteration, int executionTime) {
        this.executionTime = executionTime;
        barrier = new CyclicBarrier(carsPerIteration, this::printBreak);
        ferry = new Ferry(barrier);
        carsAdder = Executors.newCachedThreadPool();
    }

    public void start() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < executionTime) {
            addCar();
        }
        Thread.sleep(150);
        close();
    }

    private void addCar() throws InterruptedException {
        Thread.sleep(400);
        carsAdder.submit(this::wrapFerryWaiting);
    }

    private void wrapFerryWaiting() {
        Car car = new Car(barrier);
        try {
            car.waitForFerry();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void close() {
        carsAdder.shutdownNow();
        ferry.stopCarrying();
        System.out.println("Jetty closing, cars which still waiting will be forgotten.");
    }

    private void printBreak() {
        System.out.println("3 cars on jetty");
        ferry.carry();
    }
}
