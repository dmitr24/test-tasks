package siberteam.testperiod.mt.subtask.fourth.ticket;

import siberteam.testperiod.mt.subtask.util.Randomizer;

public class ParkingTicketProvider {
    public ParkingTicket getNewTicket() {
        Randomizer randomizer = new Randomizer();
        int parkingTime = randomizer.getRandomNumber(2000, 8000);
        return new ParkingTicket(parkingTime);
    }
}
