package siberteam.testperiod.mt.subtask.fourth;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicket;
import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicketProvider;
import siberteam.testperiod.mt.subtask.util.Randomizer;

@RequiredArgsConstructor
public class Car {
    private final ParkingTicketProvider parkingTicketProvider;
    private long startTimeOnParkingSpace;

    public int getTimeOnParkingSpace() {
        return (int) (System.currentTimeMillis() - startTimeOnParkingSpace);
    }

    public void park() {
        Thread.currentThread().setName("Car " + new Randomizer().getRandomNumber(0, 1000));
        System.out.println("Car (" + Thread.currentThread().getName() +
                    ") start waiting for queue");
        ParkingTicket activeTicket = null;
        try {
            activeTicket = parkingTicketProvider.getTicket();
            System.out.println("Car (" + Thread.currentThread().getName() +
                    ") on parking");
            startTimeOnParkingSpace = System.currentTimeMillis();
            Thread.sleep(activeTicket.getTimeLeft());
            System.out.println("Car (" + Thread.currentThread().getName() +
                    ") was on parking for " + getTimeOnParkingSpace());
            unPark(activeTicket);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void unPark(ParkingTicket activeTicket) throws InterruptedException {
        parkingTicketProvider.returnTicket(activeTicket);
    }
}
