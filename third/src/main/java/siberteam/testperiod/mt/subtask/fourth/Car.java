package siberteam.testperiod.mt.subtask.fourth;

import siberteam.testperiod.mt.subtask.fourth.ticket.ParkingTicket;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car {
    private final Lock parkingLock = new ReentrantLock();
    private final Condition isTurnToPark = parkingLock.newCondition();
    private Lock parkingSpaceLock;
    private Condition isParkingSpaceBusy;
    private ParkingTicket activeTicket;
    private long startTimeOnParkingSpace;
    private boolean isInQueue = true;
    private boolean isParkingCanceled = false;
    private boolean isOnParkingSpace = false;

    public void setParkingTicket(ParkingTicket parkingTicket) {
        this.activeTicket = parkingTicket;
    }

    public int getTimeOnParkingSpace() {
        return (int) (System.currentTimeMillis() - startTimeOnParkingSpace);
    }

    public void park() {
        parkingLock.lock();
        try {
            System.out.println("Car (" + Thread.currentThread().getName() +
                    ") waiting for queue");
            while (!isParkingCanceled && isInQueue) {
                isTurnToPark.await();
            }
            if (!isParkingCanceled) {
                System.out.println("Car (" + Thread.currentThread().getName() +
                        ") on parking");
                startTimeOnParkingSpace = System.currentTimeMillis();
                Thread.sleep(activeTicket.getTimeLeft());
                parkingSpaceLock.lock();
                try {
                    isOnParkingSpace = false;
                    isParkingSpaceBusy.signalAll();
                } finally {
                    parkingSpaceLock.unlock();
                }
                System.out.println("Car (" + Thread.currentThread().getName() +
                        ") was on parking for " + getTimeOnParkingSpace());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            parkingLock.unlock();
        }
    }

    public void startParking(Lock parkingSpaceLock, Condition isParkingSpaceBusy) {
        parkingLock.lock();
        try {
            this.parkingSpaceLock = parkingSpaceLock;
            this.isParkingSpaceBusy = isParkingSpaceBusy;
            isInQueue = false;
            isOnParkingSpace = true;
            isTurnToPark.signalAll();
        } finally {
            parkingLock.unlock();
        }
    }

    public boolean getIsOnParkingSpace() {
        return isOnParkingSpace;
    }

    public void stopTrying() {
        isParkingCanceled = true;
        parkingLock.lock();
        try {
            isTurnToPark.signalAll();
        } finally {
            parkingLock.unlock();
        }
    }
}
