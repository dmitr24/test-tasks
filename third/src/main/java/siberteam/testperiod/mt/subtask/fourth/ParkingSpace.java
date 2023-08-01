package siberteam.testperiod.mt.subtask.fourth;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpace {
    private final Lock parkingSpaceLock = new ReentrantLock();
    private final Condition isParkingSpaceBusy = parkingSpaceLock.newCondition();
    private final ExecutorService executorService;
    private final ArrayBlockingQueue<ParkingSpace> freeParkingSpaces;
    private Car car;
    private boolean isCanceled = false;

    public ParkingSpace(ArrayBlockingQueue<ParkingSpace> freeParkingSpaces) {
        this.freeParkingSpaces = freeParkingSpaces;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void parkCar(Car car) {
        this.car = car;
        executorService.submit(this::park);
    }

    public void close() {
        isCanceled = true;
        executorService.shutdown();
    }

    private void park() {
        parkingSpaceLock.lock();
        try {
            car.startParking(parkingSpaceLock, isParkingSpaceBusy);
            while (!isCanceled && car.getIsOnParkingSpace()) {
                isParkingSpaceBusy.await();
            }
            freeParkingSpaces.put(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            parkingSpaceLock.unlock();
        }
    }
}
