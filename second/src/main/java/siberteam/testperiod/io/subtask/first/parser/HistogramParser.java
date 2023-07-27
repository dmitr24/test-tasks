package siberteam.testperiod.io.subtask.first.parser;

import lombok.RequiredArgsConstructor;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HistogramParser {
    private final String entryPercentSign;

    public String parseToText(Map<String, Float> histogram) {
        return histogram.entrySet()
                .stream()
                .sorted((left, right) -> - Float.compare(left.getValue(), right.getValue()))
                .map(this::parseHistogramLine)
                .collect(Collectors.joining());
    }

    private String parseHistogramLine(Map.Entry<String, Float> histogramEntry) {
        StringBuilder result = new StringBuilder(histogramEntry.getKey() +
                " (" + String.format("%.2f", histogramEntry.getValue()) + "%): ");
        for (int i = 0; i < histogramEntry.getValue(); i++) {
            result.append(entryPercentSign);
        }
        result.append('\n');
        return result.toString();
    }
}
