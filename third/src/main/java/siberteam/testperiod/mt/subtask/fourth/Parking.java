package siberteam.testperiod.mt.subtask.fourth;

import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicket;
import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicketProvider;
import java.util.concurrent.ArrayBlockingQueue;

public class Parking {
    private final ParkingTicketProvider ticketProvider;
    private final ArrayBlockingQueue<Car> carsQueue;
    private final ArrayBlockingQueue<ParkingSpace> freeParkingSpaces;
    private final ParkingSpace[] parkingSpaces;
    private boolean isCancelled = false;

    public Parking(ArrayBlockingQueue<Car> carsQueue, int parkingSpacesCount) throws InterruptedException {
        this.carsQueue = carsQueue;
        this.freeParkingSpaces = new ArrayBlockingQueue<>(parkingSpacesCount, true);
        this.ticketProvider = new ParkingTicketProvider();
        this.parkingSpaces = new ParkingSpace[parkingSpacesCount];
        for (int i = 0; i < parkingSpacesCount; i++) {
            this.parkingSpaces[i] = new ParkingSpace(this.freeParkingSpaces);
            freeParkingSpaces.put(this.parkingSpaces[i]);
        }
    }

    public void park() {
        while (!isCancelled) {
            try {
                Car newCar = carsQueue.take();
                System.out.println("Cars in queue left: " + carsQueue.size());
                System.out.println("Free parking spaces count - " + freeParkingSpaces.size());
                if (freeParkingSpaces.isEmpty() ){
                    System.out.println("Waiting for free space");
                }
                ParkingSpace parkingSpace = freeParkingSpaces.take();
                System.out.println("Got free space");
                ParkingTicket newTicket = ticketProvider.getNewTicket();
                newCar.setParkingTicket(newTicket);
                parkingSpace.parkCar(newCar);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (!carsQueue.isEmpty()) {
            Car car = carsQueue.remove();
            car.stopTrying();
        }
        for (ParkingSpace parkingSpace : parkingSpaces) {
            parkingSpace.close();
        }
        System.out.println("Parking canceled");
    }

    public void cancel() {
        this.isCancelled = true;
    }
}
