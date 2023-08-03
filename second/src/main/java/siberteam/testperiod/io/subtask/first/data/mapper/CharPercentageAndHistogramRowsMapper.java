package siberteam.testperiod.io.subtask.first.data.mapper;

import siberteam.testperiod.io.subtask.first.data.visualization.CharPercentageAndHistogramRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharPercentageAndHistogramRowsMapper {
    public List<CharPercentageAndHistogramRow> map(Map<Character, Long> charCount,
                                                   long totalChars) {
        List<CharPercentageAndHistogramRow> rows = new ArrayList<>(charCount.size());
        if (totalChars == 0) {
            return rows;
        }
        for (Map.Entry<Character, Long> entry : charCount.entrySet()) {
            CharPercentageAndHistogramRow row = new CharPercentageAndHistogramRow();
            row.setSymbol(entry.getKey());
            row.setCount(entry.getValue());
            row.setPercentage(((float) entry.getValue() * 100) / totalChars);
            rows.add(row);
        }
        return rows;
    }
}
