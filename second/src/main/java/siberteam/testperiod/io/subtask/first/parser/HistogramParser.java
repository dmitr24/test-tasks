package siberteam.testperiod.io.subtask.first.parser;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class HistogramParser {
    private final String entryPercentSign;

    public String parseHistogram(Map<Character, Float> histogram) {
        List<Map.Entry<Character, Float>> histogramUnits = new ArrayList<>(histogram.entrySet());
        histogramUnits.sort((left, right) -> - Float.compare(left.getValue(), right.getValue()));
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Float> unit : histogramUnits) {
            result.append(parseHistogramLine(unit));
        }
        return result.toString();
    }



    private String parseHistogramLine(Map.Entry<Character, Float> histogramEntry) {
        StringBuilder result = new StringBuilder(histogramEntry.getKey() +
                " (" + String.format("%.2f", histogramEntry.getValue()) + "%): ");
        for (int i = 0; i < histogramEntry.getValue(); i++) {
            result.append(entryPercentSign);
        }
        result.append('\n');
        return result.toString();
    }
}
