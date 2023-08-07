package siberteam.testperiod.mt2.second.sorter;

import siberteam.testperiod.mt2.second.annotation.SorterInfo;
import java.util.List;

@SorterInfo(name = "Reversed alphabet sorter",
        description = "Sorting like alphabet sort, but reverse each word before comparing")
public class ReversedAlphabetSorter implements Sorter {
    @Override
    public List<String> sort(List<String> words) {
        words.replaceAll(this::reverseString);
        words.sort(String::compareTo);
        return words;
    }

    private String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}
