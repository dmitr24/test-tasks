package siberteam.testperiod.mt.usbtask.third;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagePrinter implements Runnable {
    private final String message;
    private final int recreationsBeforePrint;
    private final Runnable print;

    @Override
    public void run() {
        int repeation = 0;
        while (repeation < recreationsBeforePrint) {

        }
    }
}
