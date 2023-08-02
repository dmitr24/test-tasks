package siberteam.testperiod.mt.subtask.fourth;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicketProvider;
import java.util.concurrent.*;

@RequiredArgsConstructor
public class ParkingSimulation {
    private final int parkingSpacesCount;
    private final int simulationTime;
    private ExecutorService parkingExecutor;
    private Parking parking;

    public void simulate() throws InterruptedException {
        ArrayBlockingQueue<Car> carsQueue = new ArrayBlockingQueue<>(150, true);
        initializeParking(carsQueue);
        addCarsToQueue(carsQueue);
        stopParking();
    }

    private void initializeParking(ArrayBlockingQueue<Car> carsQueue) throws InterruptedException {
        ParkingTicketProvider ticketProvider = new ParkingTicketProvider(parkingSpacesCount);
        parking = new Parking(ticketProvider, carsQueue, parkingSpacesCount);
        parkingExecutor = Executors.newSingleThreadExecutor();
        parkingExecutor.submit(parking::park);
    }

    private void addCarsToQueue(ArrayBlockingQueue<Car> carsQueue) throws InterruptedException {
        int totalCars = 0;
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < simulationTime) {
            totalCars++;
            carsQueue.put(new Car());
            Thread.sleep(500);
        }
        System.out.println("Total cars: " + totalCars);
    }

    private void stopParking() {
        parking.cancel();
        parkingExecutor.shutdown();
    }
}
