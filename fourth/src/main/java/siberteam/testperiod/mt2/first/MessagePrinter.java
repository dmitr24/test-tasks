package siberteam.testperiod.mt2.first;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@RequiredArgsConstructor
public class MessagePrinter implements Runnable {
    @Getter
    @Setter
    private boolean isTimerContinue = true;
    private final String message;
    private final int recreationsBeforePrint;
    private final Lock printerLock;
    private final Condition secondLeftCondition;

    @Override
    public void run() {
        while (isTimerContinue) {
            printerLock.lock();
            try {
                int repeating = 0;
                while (isTimerContinue && (repeating < recreationsBeforePrint)) {
                    secondLeftCondition.await();
                    repeating++;
                }
                if (repeating == recreationsBeforePrint) {
                    System.out.println(message);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                printerLock.unlock();
            }
        }
    }
}
