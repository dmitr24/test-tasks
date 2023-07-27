package siberteam.testperiod.mt.usbtask.second;

public class NamePrinter {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread firstThread = new Thread(printer);
        Thread secondThread = new Thread(printer);
        firstThread.setName("Thread 1");
        secondThread.setName("Thread 2");
        firstThread.start();
        secondThread.start();
    }
}
