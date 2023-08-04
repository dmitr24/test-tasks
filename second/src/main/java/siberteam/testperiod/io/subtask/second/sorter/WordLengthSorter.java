package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.Comparator;
import java.util.List;

@SorterInfo(name = "Word length sorter", description = "Sorting by word's length")
public class WordLengthSorter implements Sorter {
    @Override
    public List<String> sort(List<String> words) {
        words.sort(Comparator.comparingInt(String::length));
        return words;
    }
}
