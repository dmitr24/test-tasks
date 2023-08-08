package siberteam.testperiod.mt2.third;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;

public class Consumer {
    private final Set<String> dictionary = ConcurrentHashMap.newKeySet();
    private final LinkedTransferQueue<String> queue;

    public Consumer(LinkedTransferQueue<String> queue) {
        this.queue = queue;
    }

    public Set<String> getDictionary() {
        return dictionary;
    }

    public void consume() {
        try {
            String word = queue.take();
            while (!word.equals("\0")) {
                dictionary.add(word);
                word = queue.take();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
