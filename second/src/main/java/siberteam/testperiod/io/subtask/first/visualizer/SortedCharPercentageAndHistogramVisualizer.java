package siberteam.testperiod.io.subtask.first.visualizer;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.first.data.SymbolPercentageAndHistogramRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SortedCharPercentageAndHistogramVisualizer implements Visualizer<String> {
    private final char entryPercentSign;

    public String visualize(Map<Character, Integer> charCount) {
        List<SymbolPercentageAndHistogramRow> rows = castToRows(charCount);
        rows.sort((left, right) -> - Float.compare(left.getPercentage(), right.getPercentage()));
        List<String> lines = castToStrings(rows);
        return String.join("\n", lines);
    }

    private List<SymbolPercentageAndHistogramRow> castToRows(Map<Character, Integer> charCount) {
        List<SymbolPercentageAndHistogramRow> rows = new ArrayList<>(charCount.size());
        int totalSymbols = getTotalSymbols(charCount);
        if (totalSymbols == 0) {
            return rows;
        }
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            SymbolPercentageAndHistogramRow row = new SymbolPercentageAndHistogramRow();
            row.setSymbol(entry.getKey());
            row.setCount(entry.getValue());
            row.setPercentage(((float) entry.getValue() * 100) / totalSymbols);
            rows.add(row);
        }
        return rows;
    }

    private List<String> castToStrings(List<SymbolPercentageAndHistogramRow> rows) {
        List<String> lines = new ArrayList<>();
        rows.forEach(row -> lines.add(visualizeRow(row)));
        return lines;
    }

    private String visualizeRow(SymbolPercentageAndHistogramRow row) {
        StringBuilder result = new StringBuilder(row.getSymbol() +
                " (" + String.format("%.2f", row.getPercentage()) + "%): ");
        for (int i = 0; i < row.getCount(); i++) {
            result.append(entryPercentSign);
        }
        return result.toString();
    }

    private int getTotalSymbols(Map<Character, Integer> entries) {
        int totalSymbols = 0;
        for (int charEntriesCount : entries.values()) {
            totalSymbols += charEntriesCount;
        }
        return totalSymbols;
    }
}
