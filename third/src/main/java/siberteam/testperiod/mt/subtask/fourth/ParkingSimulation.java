package siberteam.testperiod.mt.subtask.fourth;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.*;

@RequiredArgsConstructor
public class ParkingSimulation {
    private final int parkingSpacesCount;
    private final int simulationTime;

    public void simulate() throws InterruptedException {
        ArrayBlockingQueue<Car> carsQueue = new ArrayBlockingQueue<>(100, true);
        Parking parking = new Parking(carsQueue, parkingSpacesCount);
        ExecutorService parkingExecutor = Executors.newSingleThreadExecutor();
        parkingExecutor.submit(parking::park);
        ExecutorService carsExecutor = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < simulationTime) {
            Car newCar = new Car();
            carsExecutor.submit(newCar::park);
            carsQueue.put(newCar);
            Thread.sleep(500);
        }
        System.out.println("Cars in queue left after time's up: " + carsQueue.size());
        parking.cancel();
        parkingExecutor.shutdown();
        carsExecutor.shutdown();
    }
}
