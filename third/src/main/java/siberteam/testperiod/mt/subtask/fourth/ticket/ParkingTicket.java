package siberteam.testperiod.mt.subtask.fourth.ticket;

public class ParkingTicket {
    private final long startTime;
    private final int parkingTime;

    ParkingTicket(int time) {
        startTime = System.currentTimeMillis();
        this.parkingTime = time;
    }

    public int getTimeLeft() {
        return (int) (parkingTime + startTime - System.currentTimeMillis());
    }
}
