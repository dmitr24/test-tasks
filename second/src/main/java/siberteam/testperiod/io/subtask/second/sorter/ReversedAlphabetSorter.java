package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@SorterInfo(name = "Reversed alphabet sorter",
        description = "Sorting like alphabet sort, but reverse each word before comparing")
public class ReversedAlphabetSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .map(this::reverseString)
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    private String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}
