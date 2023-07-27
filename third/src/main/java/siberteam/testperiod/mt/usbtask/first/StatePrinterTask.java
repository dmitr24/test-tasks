package siberteam.testperiod.mt.usbtask.first;

public class StatePrinterTask {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(200);
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    };

}
