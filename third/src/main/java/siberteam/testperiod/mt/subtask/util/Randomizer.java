package siberteam.testperiod.mt.subtask.util;

import java.util.Random;

public class Randomizer {
    private final Random random;

    public Randomizer() {
        this.random = new Random();
    }

    public int getRandomNumber(int leftBorder, int rightBorder) {
        return random.ints(leftBorder, rightBorder)
                .findAny()
                .orElse(0);
    }
}
