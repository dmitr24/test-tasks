package siberteam.testperiod.mt.usbtask.fifth;

import java.util.concurrent.CyclicBarrier;

public class Jetty {
    private final CyclicBarrier barrier;
    private final Ferry ferry;

    public Jetty(int carsPerIteration) {
        barrier = new CyclicBarrier(carsPerIteration, this::printBreak);
        ferry = new Ferry(barrier);
    }

    public void start() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60_000) {
            addCar();
        }
        ferry.stopCarrying();
    }

    private void addCar() throws InterruptedException {
        Car car = new Car(barrier);
        Thread.sleep(400);
        new Thread(car::waitForFerry).start();
    }

    private void printBreak() {
        System.out.println("3 cars on jetty");
        ferry.carry();
    }
}
