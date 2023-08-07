package siberteam.testperiod.mt2.second.sorter;

import siberteam.testperiod.mt2.second.annotation.SorterInfo;
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
