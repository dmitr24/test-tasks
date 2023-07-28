package siberteam.testperiod.mt.subtask.second;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer implements Runnable {
    private final Lock printLock = new ReentrantLock();
    private final Condition isFirstDone = printLock.newCondition();
    private boolean isFirstThreadPrinted = false;

    @Override
    public void run() {
        printLock.lock();
        if (Thread.currentThread().getName().equals("Thread 1")) {
            System.out.println(Thread.currentThread().getName());
            isFirstThreadPrinted = true;
            isFirstDone.signalAll();
        } else {
            try {
                while(!isFirstThreadPrinted) {
                    isFirstDone.await();
                }
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        printLock.unlock();
    }
}
