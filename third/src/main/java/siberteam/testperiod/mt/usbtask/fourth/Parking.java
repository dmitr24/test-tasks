package siberteam.testperiod.mt.usbtask.fourth;

import siberteam.testperiod.mt.usbtask.util.Randomizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Parking {
    private final ReadWriteLock parkingLock= new ReentrantReadWriteLock();
    private final boolean[] parkingSpaces;

    public Parking(boolean[] parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public void park() {
        int parkingSpaceIndex = getParkingSpace();
        holdParkingSpace();
        leaveParkingSpace(parkingSpaceIndex);
    }

    private int getParkingSpace() {
        System.out.println("Car (" + Thread.currentThread().getName() + ") looking for park space");
        boolean success = false;
        int freeSpaceIndex = -1;
        while (!success) {
            freeSpaceIndex = waitForFreeSpace();
            success = makeParkingAttempt(freeSpaceIndex);
        }
        System.out.println("Car (" + Thread.currentThread().getName() + ") parked successfully");
        return freeSpaceIndex;
    }

    private void holdParkingSpace() {
        Randomizer randomizer = new Randomizer();
        int sleepTime = randomizer.getRandomNumber(2000, 8000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException exception) {
            System.out.println("Car (" + Thread.currentThread().getName() + ") can't park." +
                    " Waiting interruption.");
            System.exit(1);
        }
    }

    private void leaveParkingSpace(int parkingSpaceIndex) {
        parkingLock.writeLock().lock();
        try {
            parkingSpaces[parkingSpaceIndex] =  false;
            System.out.println("Car (" + Thread.currentThread().getName() + ") removed successfully");
        } finally {
            parkingLock.writeLock().unlock();
        }
    }

    private boolean makeParkingAttempt(int freeSpaceIndex) {
        parkingLock.writeLock().lock();
        try {
            if (!parkingSpaces[freeSpaceIndex]) {
                parkingSpaces[freeSpaceIndex] = true;
                return true;
            } else {
                return false;
            }
        } finally {
            parkingLock.writeLock().unlock();
        }
    }

    public int waitForFreeSpace() {
        int freeSpaceIdex = getFreeSpaceIndex();
        while (freeSpaceIdex == -1) {
            freeSpaceIdex = getFreeSpaceIndex();
        }
        return freeSpaceIdex;
    }

    public int getFreeSpaceIndex() {
        Lock readLock = parkingLock.readLock();
        try {
            readLock.lock();
            int freeIndex = -1;
            for (int i = 0; i < parkingSpaces.length; i++) {
                if (!parkingSpaces[i]) {
                    freeIndex = i;
                    break;
                }
            }
            return freeIndex;
        } finally {
            readLock.unlock();
        }
    }
}
