package siberteam.testperiod.mt.subtask.fourth;

import java.util.Scanner;

public class ParkingTask {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Please, enter the number of parking places: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int simulationTime = 10_000;
        ParkingSimulation parkingSimulation = new ParkingSimulation(n, simulationTime);
        parkingSimulation.simulate();
    }
}
