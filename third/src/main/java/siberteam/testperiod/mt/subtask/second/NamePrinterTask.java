package siberteam.testperiod.mt.subtask.second;

public class NamePrinterTask {
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
