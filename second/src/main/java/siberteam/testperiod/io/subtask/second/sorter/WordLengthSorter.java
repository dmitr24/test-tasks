package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@SorterInfo(name = "Word length sorter", description = "Sorting by word's length")
public class WordLengthSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.joining("\n"));
    }
}
