package siberteam.testperiod.mt.usbtask.third;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@RequiredArgsConstructor
public class TimerNotifier implements Runnable {
    private final Lock printerLock;
    private final Condition secondLeftCondition;
    private final int executionTime;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < executionTime) {
            try {
                Thread.sleep(1000);
                printerLock.lock();
                try {
                    System.out.println(((System.currentTimeMillis() - startTime) / 1000) + "s left");
                    secondLeftCondition.signalAll();
                } finally {
                    printerLock.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}