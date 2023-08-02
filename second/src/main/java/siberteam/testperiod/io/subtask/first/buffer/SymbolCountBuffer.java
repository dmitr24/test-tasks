package siberteam.testperiod.io.subtask.first.buffer;

import java.util.HashMap;
import java.util.Map;

public class SymbolCountBuffer {
    private final Map<Character, Integer> symbolCount;

    public SymbolCountBuffer() {
        this.symbolCount = new HashMap<>();
    }

    public Map<Character, Integer> getActualState() {
        return symbolCount;
    }

    public void append(char newChar) {
        if (symbolCount.containsKey(newChar)) {
                symbolCount.put(newChar, symbolCount.get(newChar) + 1);
        } else {
            symbolCount.put(newChar, 1);
        }
    }
}
