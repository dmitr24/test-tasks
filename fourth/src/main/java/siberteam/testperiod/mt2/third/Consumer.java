package siberteam.testperiod.mt2.third;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TransferQueue;

public class Consumer {
    private final Set<String> dictionary = ConcurrentHashMap.newKeySet();
    private final TransferQueue<String> queue;

    public Consumer(TransferQueue<String> queue) {
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
