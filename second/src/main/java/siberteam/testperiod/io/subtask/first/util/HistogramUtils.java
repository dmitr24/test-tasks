package siberteam.testperiod.io.subtask.first.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.NONE)
public class HistogramUtils {
    private static final String ENTRY_PERCENT_SIGN = "#";

    public static String parseToText(Map<String, Float> histogram) {
        return histogram.entrySet()
                .stream()
                .sorted((left, right) -> - Float.compare(left.getValue(), right.getValue()))
                .map(HistogramUtils::parseHistogramLine)
                .collect(Collectors.joining());
    }

    private static String parseHistogramLine(Map.Entry<String, Float> histogramEntry) {
        StringBuilder result = new StringBuilder(histogramEntry.getKey() +
                " (" + String.format("%.2f", histogramEntry.getValue()) + "%): ");
        for (int i = 0; i < histogramEntry.getValue(); i++) {
            result.append(ENTRY_PERCENT_SIGN);
        }
        result.append('\n');
        return result.toString();
    }
}
