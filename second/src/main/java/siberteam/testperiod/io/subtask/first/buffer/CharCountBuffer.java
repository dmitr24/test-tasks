package siberteam.testperiod.io.subtask.first.buffer;

import java.util.HashMap;
import java.util.Map;

public class CharCountBuffer {
    private final Map<Character, Integer> charCount;

    public CharCountBuffer() {
        this.charCount = new HashMap<>();
    }

    public Map<Character, Integer> getActualState() {
        return charCount;
    }

    public void append(Character newChar) {
        if (charCount.containsKey(newChar)) {
            charCount.put(newChar, charCount.get(newChar) + 1);
        } else {
            charCount.put(newChar, 1);
        }
    }
}
