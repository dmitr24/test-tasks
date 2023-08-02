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

    public void append(String line) {
        char[] chars = line.toCharArray();
        for (char character : chars) {
            if (symbolCount.containsKey(character)) {
                symbolCount.put(character, symbolCount.get(character) + 1);
            } else {
                symbolCount.put(character, 1);
            }
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
