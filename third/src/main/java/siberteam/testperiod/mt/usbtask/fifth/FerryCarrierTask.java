package siberteam.testperiod.mt.usbtask.fifth;

public class FerryCarrierTask {
    public static void main(String[] args) throws InterruptedException {
        int maxCarsPerCarrying = 3;
        Jetty jetty = new Jetty(maxCarsPerCarrying);
        jetty.start();
    }
}
