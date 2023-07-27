package siberteam.testperiod.io.subtask.first.domain;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Analyzer {
    public Map<String, Float> analyze(Stream<String> textLines, long linesCount) {
        return textLines
                .map(this::analyzeLine)
                .reduce(this::mergeTwoAnalytics)
                .map(histogram -> summarizeAnalytics(histogram, linesCount))
                .orElseGet(HashMap::new);
    }

    private Map<String, Float> summarizeAnalytics(Map<String, Float> histogram, long totalLines) {
        for (Map.Entry<String, Float> entry : histogram.entrySet()) {
            entry.setValue(entry.getValue() / totalLines);
        }
        return histogram;
    }

    private Map<String, Float> mergeTwoAnalytics(Map<String, Float> leftLineHistogram,
                                              Map<String, Float> rightLineHistogram) {
        leftLineHistogram.forEach((key, value) -> rightLineHistogram.merge(key, value, Float::sum));
        return rightLineHistogram;
    }

    private Map<String, Float> analyzeLine(String line) {
        Text text = new Text(line);
        List<String> distinctLetters = text.getDistinctLetters();
        Map<String, Float> result = new HashMap<>();
        if (distinctLetters.size() == 1 && distinctLetters.get(0).equals("")) {
            return result;
        }
        int textLength = line.length();
        for (String distinctLetter : distinctLetters) {
            int count = textLength - line.replace(distinctLetter, "").length();
            float percentage =  ((float) count) / textLength * 100;
            result.put(distinctLetter, percentage);
        }
        return result;
    }
}
