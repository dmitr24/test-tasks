package siberteam.testperiod.mt.subtask.fourth;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicketProvider;

import java.util.concurrent.*;

@RequiredArgsConstructor
public class ParkingSimulation {
    private final int parkingSpacesCount;
    private final int simulationTime;

    public void simulate() throws InterruptedException {
        ParkingTicketProvider ticketProvider = new ParkingTicketProvider(parkingSpacesCount);
        ExecutorService carsExecutor = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < simulationTime) {
            Car newCar = new Car(ticketProvider);
            carsExecutor.submit(newCar::park);
            Thread.sleep(500);
        }
        carsExecutor.shutdown();
    }
}
