package siberteam.testperiod.mt.usbtask.fourth;

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
        boolean success = false;
        while (!success) {
            int freeSpaceIndex = waitForFreeSpace();
            success = makeParkingAttempt(freeSpaceIndex);
        }
    }

    private boolean makeParkingAttempt(int freeSpaceIndex) {
        try {
            parkingLock.writeLock().lock();
            if (!parkingSpaces[freeSpaceIndex]) {
                parkingSpaces[freeSpaceIndex] = true;
                return true;
            }
        } finally {
            parkingLock.writeLock().unlock();
        }
        return false;
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
                if (parkingSpaces[i]) {
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
