package siberteam.testperiod.mt.usbtask.first;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StateSimulator {
    public void simulate() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                lock.lock();
                condition.await();
                lock.unlock();
                synchronized (lock) {}
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        Thread.sleep(200);
        System.out.println(thread.getState());
        lock.lock();
        Thread.sleep(900);
        System.out.println(thread.getState());
        lock.unlock();
        Thread.sleep(200);
        lock.lock();
        synchronized (lock) {
            condition.signal();
            lock.unlock();
            Thread.sleep(200);
            System.out.println(thread.getState());
        }
        thread.join();
        System.out.println(thread.getState());
    }
}