package siberteam.testperiod.mt.subtask.fourth;

import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicketProvider;
import java.util.concurrent.*;

public class Parking {
    private final ParkingTicketProvider ticketProvider;
    private final ArrayBlockingQueue<Car> carsQueue;
    private boolean isCanceled = false;

    public Parking(ParkingTicketProvider ticketProvider, ArrayBlockingQueue<Car> carsQueue,
                   int parkingSpacesCount) {
        this.ticketProvider = ticketProvider;
        this.carsQueue = carsQueue;
    }

    public void park() {
        try {
            while (!isCanceled) {
                Car car = getCarWithTicket();
                takeParkingSpace(car);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Car getCarWithTicket() throws InterruptedException {
        Car car = carsQueue.take();
        car.acquireTicket(ticketProvider);
        return car;
    }

    private void takeParkingSpace(Car car) {
        CompletableFuture
                .supplyAsync(car::comeOnParkingSpace)
                .thenAcceptAsync((ticket) -> {
                    try {
                        ticketProvider.returnTicket(ticket);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void cancel() {
        isCanceled = true;
    }
}
