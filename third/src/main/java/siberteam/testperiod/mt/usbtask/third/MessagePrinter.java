package siberteam.testperiod.mt.usbtask.third;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@RequiredArgsConstructor
public class MessagePrinter implements Runnable {
    private final String message;
    private final int recreationsBeforePrint;
    private final Lock printerLock;
    private final Condition secondLeftCondition;
    private final int executionTime;

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < executionTime) {
            printerLock.lock();
            try {
                int repeating = 0;
                while (repeating < recreationsBeforePrint) {
                    secondLeftCondition.await();
                    repeating++;
                }
                System.out.println(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                printerLock.unlock();
            }
        }
    }
}
