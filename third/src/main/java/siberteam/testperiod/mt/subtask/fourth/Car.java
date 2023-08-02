package siberteam.testperiod.mt.subtask.fourth;

import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicket;
import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicketProvider;
import siberteam.testperiod.mt.subtask.util.Randomizer;

public class Car {
    private long startTimeOnParkingSpace;
    private ParkingTicket activeTicket;

    public int getTimeOnParkingSpace() {
        return (int) (System.currentTimeMillis() - startTimeOnParkingSpace);
    }

    public ParkingTicket comeOnParkingSpace() {
        Thread.currentThread().setName("Car " + new Randomizer().getRandomNumber(0, 1000));
        try {
            System.out.println("Car (" + Thread.currentThread().getName() +
                    ") on parking");
            startTimeOnParkingSpace = System.currentTimeMillis();
            Thread.sleep(activeTicket.getTimeLeft());
            System.out.println("Car (" + Thread.currentThread().getName() +
                    ") was on parking for " + getTimeOnParkingSpace());
            return activeTicket;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void acquireTicket(ParkingTicketProvider ticketProvider) throws InterruptedException {
        this.activeTicket = ticketProvider.getTicket();
    }
}
