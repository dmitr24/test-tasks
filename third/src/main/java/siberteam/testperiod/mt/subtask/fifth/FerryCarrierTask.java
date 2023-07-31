package siberteam.testperiod.mt.subtask.fifth;

public class FerryCarrierTask {
    public static void main(String[] args) throws InterruptedException {
        int maxCarsPerCarrying = 3;
        Jetty jetty = new Jetty(maxCarsPerCarrying, 11_400);
        jetty.start();
    }
}
