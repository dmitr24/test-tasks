package siberteam.testperiod.io.subtask.first.domain;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.HashMap;
import java.util.List;
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
        Text text = new Text(line);
        List<Character> distinctLetters = text.getDistinctLettersWithoutStream();
        Map<Character, Integer> newLineStatistics = new HashMap<>();
        int lineLength = line.length();
        for (char distinctLetter : distinctLetters) {
            int entries = lineLength - line.replace(String.valueOf(distinctLetter),
                    "").length();
            newLineStatistics.put(distinctLetter, entries);
        }
        mergeNewStatistics(newLineStatistics);
    }

    private void mergeNewStatistics(Map<Character, Integer> rightLineHistogram) {
        rightLineHistogram.forEach((key, value) -> symbolCount.merge(key, value, Integer::sum));
    }

    private int getTotalSymbols(Map<Character, Integer> entries) {
        int totalSymbols = 0;
        for (int letterEntriesCount : entries.values()) {
            totalSymbols += letterEntriesCount;
        }
        return totalSymbols;
    }
}
