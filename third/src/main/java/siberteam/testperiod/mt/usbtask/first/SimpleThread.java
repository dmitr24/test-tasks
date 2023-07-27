package siberteam.testperiod.mt.usbtask.first;

import lombok.Getter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class SimpleThread extends Thread {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

    }
}
