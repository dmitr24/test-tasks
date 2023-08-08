package siberteam.testperiod.mt2.third;

import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer {
    private final Set<String> dictionary = ConcurrentHashMap.newKeySet();
    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
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
