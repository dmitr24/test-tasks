package siberteam.testperiod.mt.subtask.second;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer implements Runnable {
    private final Lock printLock = new ReentrantLock();
    private final Condition isFirstDone = printLock.newCondition();
    private final Condition isSecondDone = printLock.newCondition();
    private boolean isSecondThreadTurn = false;
    private boolean isClosing = false;

    public void close() {
        this.isClosing = true;
    }

    @Override
    public void run() {
        while (!isClosing) {
            printLock.lock();
            try {
                if (Thread.currentThread().getName().equals("Thread 1")) {
                    while (isSecondThreadTurn) {
                        isSecondDone.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    isSecondThreadTurn = true;
                    isFirstDone.signalAll();
                } else if (Thread.currentThread().getName().equals("Thread 2")){
                    while (!isSecondThreadTurn) {
                        isFirstDone.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("--------");
                    isSecondThreadTurn = false;
                    isSecondDone.signalAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                printLock.unlock();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
