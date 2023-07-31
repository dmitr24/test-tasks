package siberteam.testperiod.mt.subtask.fourth;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public class ParkingSimulation {
    private final int parkingSpacesCount;
    private final int simulationTime;

    public void simulate() throws InterruptedException {
        boolean[] parkingSpaces = new boolean[parkingSpacesCount];
        Parking parking = new Parking(parkingSpaces);
        ExecutorService executorService = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < simulationTime) {
            executorService.submit(parking::park);
            Thread.sleep(500);
        }
        executorService.shutdown();
    }
}
