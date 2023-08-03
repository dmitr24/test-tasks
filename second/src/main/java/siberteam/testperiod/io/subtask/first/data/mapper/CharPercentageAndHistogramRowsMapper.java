package siberteam.testperiod.io.subtask.first.data.mapper;

import siberteam.testperiod.io.subtask.first.data.visualization.CharPercentageAndHistogramRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharPercentageAndHistogramRowsMapper implements
        DataMapper<List<CharPercentageAndHistogramRow>> {
    public List<CharPercentageAndHistogramRow> map(Map<Character, Integer> charCount) {
        List<CharPercentageAndHistogramRow> rows = new ArrayList<>(charCount.size());
        int totalSymbols = getTotalSymbols(charCount);
        if (totalSymbols == 0) {
            return rows;
        }
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            CharPercentageAndHistogramRow row = new CharPercentageAndHistogramRow();
            row.setSymbol(entry.getKey());
            row.setCount(entry.getValue());
            row.setPercentage(((float) entry.getValue() * 100) / totalSymbols);
            rows.add(row);
        }
        return rows;
    }

    private int getTotalSymbols(Map<Character, Integer> entries) {
        int totalSymbols = 0;
        for (int charEntriesCount : entries.values()) {
            totalSymbols += charEntriesCount;
        }
        return totalSymbols;
    }
}
