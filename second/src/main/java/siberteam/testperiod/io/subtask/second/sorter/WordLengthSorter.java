package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class WordLengthSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.joining("\n"));
    }
}
