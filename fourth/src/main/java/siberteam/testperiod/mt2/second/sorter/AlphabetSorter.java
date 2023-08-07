package siberteam.testperiod.mt2.second.sorter;

import siberteam.testperiod.mt2.second.annotation.SorterInfo;
import java.util.List;

@SorterInfo(name = "Alphabet sorter", description = "Sorting by alphabet order from A to Z")
public class AlphabetSorter implements Sorter {
    @Override
    public List<String> sort(List<String> words) {
        words.sort(String::compareTo);
        return words;
    }
}
