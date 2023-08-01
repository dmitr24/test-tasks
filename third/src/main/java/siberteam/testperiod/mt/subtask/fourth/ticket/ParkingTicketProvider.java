package siberteam.testperiod.mt.subtask.fourth.ticket;

import siberteam.testperiod.mt.subtask.util.Randomizer;
import java.util.concurrent.ArrayBlockingQueue;

public class ParkingTicketProvider {
    private final ArrayBlockingQueue<ParkingTicket> freeTickets;

    public ParkingTicketProvider(int ticketsNumber) throws InterruptedException {
        freeTickets = new ArrayBlockingQueue<>(ticketsNumber, true);
        for (int i = 0; i < ticketsNumber; i++) {
            freeTickets.put(getNewTicket());
        }
    }

    public ParkingTicket getTicket() throws InterruptedException {
        ParkingTicket parkingTicket =  freeTickets.take();
        parkingTicket.refresh();
        return parkingTicket;
    }

    public void returnTicket(ParkingTicket parkingTicket) throws InterruptedException {
        freeTickets.put(parkingTicket);
    }

    private ParkingTicket getNewTicket() {
        Randomizer randomizer = new Randomizer();
        int parkingTime = randomizer.getRandomNumber(2000, 8000);
        return new ParkingTicket(parkingTime);
    }
}
