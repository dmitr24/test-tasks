package siberteam.testperiod.mt.usbtask.third;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimingTask {
    public static void main(String[] args) {
        int executionTime = 60_000;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        MessagePrinter eachFiveSecondPrinter =
                new MessagePrinter("I'm printing each 5 seconds", 5,
                        lock, condition, executionTime);
        MessagePrinter eachSevenSecondsMessagePrinter =
                new MessagePrinter("I'm printing each 7 seconds", 7,
                        lock, condition, executionTime);
        new Thread(eachFiveSecondPrinter).start();
        new Thread(eachSevenSecondsMessagePrinter).start();
        TimerNotifier timer = new TimerNotifier(lock, condition, executionTime);
        timer.run();
    }
}
