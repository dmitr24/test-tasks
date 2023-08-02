package siberteam.testperiod.mt2.first;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimingTask {
    public static void main(String[] args) {
        int executionTime = 10_000;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        MessagePrinter eachFiveSecondPrinter =
                new MessagePrinter("I'm printing each 5 seconds", 5,
                        lock, condition);
        MessagePrinter eachSevenSecondsMessagePrinter =
                new MessagePrinter("I'm printing each 7 seconds", 7,
                        lock, condition);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(eachFiveSecondPrinter);
        executorService.submit(eachSevenSecondsMessagePrinter);
        TimerNotifier timer = new TimerNotifier(lock, condition, executionTime);
        timer.start();
        eachFiveSecondPrinter.setTimerContinue(false);
        eachSevenSecondsMessagePrinter.setTimerContinue(false);
        executorService.shutdown();
    }
}
