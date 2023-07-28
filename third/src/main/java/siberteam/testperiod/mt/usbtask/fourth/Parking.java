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
        System.out.println("Car (" + Thread.currentThread().getName() + ") looking for park space");
        boolean success = false;
        int freeSpaceIndex = -1;
        while (!success) {
            freeSpaceIndex = waitForFreeSpace();
            success = makeParkingAttempt(freeSpaceIndex);
        }
        System.out.println("Car (" + Thread.currentThread().getName() + ") parked successfully");
        Randomizer randomizer = new Randomizer();
        int sleepTime = randomizer.getRandomNumber(2000, 8000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException exception) {
            System.out.println("Car (" + Thread.currentThread().getName() + ") can't park." +
                    " Waiting interruption.");
            System.exit(1);
        }
        try {
            parkingLock.writeLock().lock();
            parkingSpaces[freeSpaceIndex] =  false;
            System.out.println("Car (" + Thread.currentThread().getName() + ") removed successfully");
        } finally {
            parkingLock.writeLock().unlock();
        }
    }

    private boolean makeParkingAttempt(int freeSpaceIndex) {
        try {
            parkingLock.writeLock().lock();
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
        readLock.lock();
        int freeIndex = -1;
        try {
            for (int i = 0; i < parkingSpaces.length; i++) {
                if (!parkingSpaces[i]) {
                    freeIndex = i;
                    break;
                }
            }
        } finally {
            readLock.unlock();
        }
        return freeIndex;
    }
}
