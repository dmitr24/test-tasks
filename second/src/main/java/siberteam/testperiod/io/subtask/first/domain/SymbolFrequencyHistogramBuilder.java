package siberteam.testperiod.io.subtask.first.domain;

import java.util.HashMap;
import java.util.Map;

public class SymbolFrequencyHistogramBuilder {
    private final Map<Character, Integer> symbolCount;

    public SymbolFrequencyHistogramBuilder() {
        this.symbolCount = new HashMap<>();
    }

    public Map<Character, Float> build() {
        Map<Character, Float> histogram = new HashMap<>();
        int totalSymbols = getTotalSymbols(symbolCount);
        if (totalSymbols == 0) {
            return histogram;
        }
        for (Map.Entry<Character, Integer> entry : symbolCount.entrySet()) {
            histogram.put(entry.getKey(), ((float) entry.getValue() * 100) / totalSymbols);
        }
        return histogram;
    }

    public void append(char newChar) {
        if (symbolCount.containsKey(newChar)) {
                symbolCount.put(newChar, symbolCount.get(newChar) + 1);
        } else {
            symbolCount.put(newChar, 1);
        }
    }

    private int getTotalSymbols(Map<Character, Integer> entries) {
        int totalSymbols = 0;
        for (int letterEntriesCount : entries.values()) {
            totalSymbols += letterEntriesCount;
        }
        return totalSymbols;
    }
}
