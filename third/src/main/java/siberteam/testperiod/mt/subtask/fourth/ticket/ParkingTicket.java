package siberteam.testperiod.mt.subtask.fourth.ticket;

public class ParkingTicket {
    private long startTime;
    private final int parkingTime;

    ParkingTicket(int time) {
        startTime = System.currentTimeMillis();
        this.parkingTime = time;
    }

    public int getTimeLeft() {
        return (int) (parkingTime + startTime - System.currentTimeMillis());
    }

    public void refresh() {
        this.startTime = System.currentTimeMillis();
    }
}
