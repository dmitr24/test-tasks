package siberteam.testperiod.io.subtask.first.domain;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Analyzer {
    public Map<Character, Float> analyze(Stream<String> textLines) {
        return textLines
                .map(this::analyzeLine)
                .reduce(this::mergeTwoAnalytics)
                .map(this::summarizeAnalytics)
                .orElseGet(HashMap::new);
    }

    private Map<Character, Float> summarizeAnalytics(Map<Character, Integer> entries) {
        Map<Character, Float> histogram = new HashMap<>();
        int totalSymbols = entries
                .values()
                .stream()
                .reduce(Integer::sum)
                .orElse(0);
        if (totalSymbols == 0) {
            return histogram;
        }
        for (Map.Entry<Character, Integer> entry : entries.entrySet()) {
            histogram.put(entry.getKey(), ((float) entry.getValue() * 100) / totalSymbols);
        }
        return histogram;
    }

    private Map<Character, Integer> mergeTwoAnalytics(Map<Character, Integer> leftLineHistogram,
                                              Map<Character, Integer> rightLineHistogram) {
        leftLineHistogram.forEach((key, value) -> rightLineHistogram.merge(key, value, Integer::sum));
        return rightLineHistogram;
    }

    private Map<Character, Integer> analyzeLine(String line) {
        Text text = new Text(line);
        List<Character> distinctLetters = text.getDistinctLetters();
        Map<Character, Integer> result = new HashMap<>();
        if (distinctLetters.size() == 1 && (distinctLetters.get(0) == null || distinctLetters.get(0).equals(' '))) {
            return result;
        }
        int textLength = line.length();
        for (char distinctLetter : distinctLetters) {
            int entries = textLength - line.replace(String.valueOf(distinctLetter),
                    "").length();
            result.put(distinctLetter, entries);
        }
        System.out.println(result);
        return result;
    }
}
