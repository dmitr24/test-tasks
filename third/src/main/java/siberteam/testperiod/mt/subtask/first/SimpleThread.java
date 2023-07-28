package siberteam.testperiod.mt.subtask.first;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.locks.Lock;

@Getter
@RequiredArgsConstructor
public class SimpleThread implements Runnable {
    private final Lock lock;

    @Override
    public void run() {
        lock.lock();
        lock.unlock();
    }
}
