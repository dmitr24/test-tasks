package siberteam.testperiod.io.subtask.first.buffer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class CharCountBuffer {
    private final Map<Character, LongAdder> charsCount;
    private final LongAdder allCharsEntriesCount;

    public CharCountBuffer() {
        this.charsCount = new ConcurrentHashMap<>();
        this.allCharsEntriesCount = new LongAdder();
    }

    public Map<Character, Long> getActualState() {
        Map<Character, Long> snapshot = new HashMap<>();
        for (Map.Entry<Character, LongAdder> charCount : charsCount.entrySet()) {
            snapshot.put(charCount.getKey(), charCount.getValue().sum());
        }
        return snapshot;
    }

    public long getTotalCharsCount() {
        return allCharsEntriesCount.sum();
    }

    public void append(Character newChar) {
        allCharsEntriesCount.increment();
        charsCount.computeIfAbsent(newChar, k -> new LongAdder()).increment();
    }
}
