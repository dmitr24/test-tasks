package siberteam.testperiod.io.subtask.first.visualizer;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.first.data.visualization.CharPercentageAndHistogramRow;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SortedCharPercentageAndHistogramVisualizer {
    private final char entryPercentSign;

    public String visualize(List<CharPercentageAndHistogramRow> rows) {
        rows.sort((left, right) -> - Float.compare(left.getPercentage(), right.getPercentage()));
        List<String> lines = castToStrings(rows);
        return String.join("\n", lines);
    }

    private List<String> castToStrings(List<CharPercentageAndHistogramRow> rows) {
        List<String> lines = new ArrayList<>();
        rows.forEach(row -> lines.add(visualizeRow(row)));
        return lines;
    }

    private String visualizeRow(CharPercentageAndHistogramRow row) {
        StringBuilder result = new StringBuilder(row.getSymbol() +
                " (" + String.format("%.2f", row.getPercentage()) + "%): ");
        for (int i = 0; i < row.getCount(); i++) {
            result.append(entryPercentSign);
        }
        return result.toString();
    }
}
