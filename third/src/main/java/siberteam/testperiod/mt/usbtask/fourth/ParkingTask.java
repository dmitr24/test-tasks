package siberteam.testperiod.mt.usbtask.fourth;

import siberteam.testperiod.mt.usbtask.util.Randomizer;
import java.util.Scanner;

public class ParkingTask {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Please, enter the number of parking places: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        boolean[] parkingPlace = new boolean[n];
        Parking parking = new Parking(parkingPlace);
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60_000) {
            new Thread(parking::park).start();
            Thread.sleep(500);
        }
    }
}
