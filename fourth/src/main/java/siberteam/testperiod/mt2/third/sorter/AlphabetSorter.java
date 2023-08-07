package siberteam.testperiod.mt2.third.sorter;

import java.util.List;

public class AlphabetSorter {
    public List<String> sort(List<String> words) {
        words.sort(String::compareTo);
        return words;
    }
}
