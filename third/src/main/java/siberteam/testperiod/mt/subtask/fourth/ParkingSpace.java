package siberteam.testperiod.mt.subtask.fourth;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParkingSpace {
    private final ExecutorService executorService;
    private final ArrayBlockingQueue<ParkingSpace> freeParkingSpaces;
    private Car car;

    public ParkingSpace(ArrayBlockingQueue<ParkingSpace> freeParkingSpaces) {
        this.freeParkingSpaces = freeParkingSpaces;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void parkCar(Car car) {
        this.car = car;
        executorService.submit(this::park);
    }

    public void close() {
        executorService.shutdown();
    }

    private void park() {
        car.startParking(this);
    }

    public void makeFree() throws InterruptedException {
        freeParkingSpaces.put(this);
    }
}
