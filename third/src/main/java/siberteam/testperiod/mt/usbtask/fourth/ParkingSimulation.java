package siberteam.testperiod.mt.usbtask.fourth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParkingSimulation {
    private final int parkingSpacesCount;

    public void simulate() throws InterruptedException {
        boolean[] parkingSpaces = new boolean[parkingSpacesCount];
        Parking parking = new Parking(parkingSpaces);
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60_000) {
            new Thread(parking::park).start();
            Thread.sleep(500);
        }
    }
}
