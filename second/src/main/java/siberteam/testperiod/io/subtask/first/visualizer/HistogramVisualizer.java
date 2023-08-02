package siberteam.testperiod.io.subtask.first.visualizer;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class HistogramVisualizer implements Visualizer {
    private final String entryPercentSign;

    public String visualize(Map<Character, Integer> symbolCount) {
        Map<Character, Float> histogram = castCharCountToPercentage(symbolCount);
        List<Map.Entry<Character, Float>> histogramUnits = new ArrayList<>(histogram.entrySet());
        histogramUnits.sort((left, right) -> - Float.compare(left.getValue(), right.getValue()));
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Float> unit : histogramUnits) {
            result.append(visualizeLine(unit));
        }
        return result.toString();
    }

    private Map<Character, Float> castCharCountToPercentage(Map<Character, Integer> symbolCount) {
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

    private String visualizeLine(Map.Entry<Character, Float> histogramEntry) {
        StringBuilder result = new StringBuilder(histogramEntry.getKey() +
                " (" + String.format("%.2f", histogramEntry.getValue()) + "%): ");
        for (int i = 0; i < histogramEntry.getValue(); i++) {
            result.append(entryPercentSign);
        }
        result.append('\n');
        return result.toString();
    }

    private int getTotalSymbols(Map<Character, Integer> entries) {
        int totalSymbols = 0;
        for (int letterEntriesCount : entries.values()) {
            totalSymbols += letterEntriesCount;
        }
        return totalSymbols;
    }
}
