package siberteam.testperiod.io.subtask.first.domain;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Analyzer {
    public Map<String, Float> analyze(Stream<String> textLines) {
        return textLines
                .map(this::analyzeLine)
                .reduce(this::mergeTwoAnalytics)
                .map(this::summarizeAnalytics)
                .orElseGet(HashMap::new);
    }

    private Map<String, Float> summarizeAnalytics(Map<String, Integer> entries) {
        Map<String, Float> histogram = new HashMap<>();
        int totalSymbols = entries.values().stream().reduce(Integer::sum).get();
        for (Map.Entry<String, Integer> entry : entries.entrySet()) {
            histogram.put(entry.getKey(), ((float) entry.getValue() * 100) / totalSymbols);
        }
        return histogram;
    }

    private Map<String, Integer> mergeTwoAnalytics(Map<String, Integer> leftLineHistogram,
                                              Map<String, Integer> rightLineHistogram) {
        leftLineHistogram.forEach((key, value) -> rightLineHistogram.merge(key, value, Integer::sum));
        return rightLineHistogram;
    }

    private Map<String, Integer> analyzeLine(String line) {
        Text text = new Text(line);
        List<String> distinctLetters = text.getDistinctLetters();
        Map<String, Integer> result = new HashMap<>();
        if (distinctLetters.size() == 1 && distinctLetters.get(0).equals("")) {
            return result;
        }
        int textLength = line.length();
        for (String distinctLetter : distinctLetters) {
            int entries = textLength - line.replace(distinctLetter, "").length();
            result.put(distinctLetter, entries);
        }
        return result;
    }
}
