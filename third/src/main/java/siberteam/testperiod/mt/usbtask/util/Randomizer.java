package siberteam.testperiod.mt.usbtask.util;

import java.util.Random;

public class Randomizer {
    private Random random;

    public Randomizer() {
        this.random = new Random();
    }

    public int getRandomNumber(int leftBorder, int rightBorder) {
        return random.ints(leftBorder, rightBorder)
                .findAny()
                .getAsInt();
    }
}
