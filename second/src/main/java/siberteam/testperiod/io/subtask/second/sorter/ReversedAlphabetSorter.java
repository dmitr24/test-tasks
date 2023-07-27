package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ReversedAlphabetSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .map(ReversedAlphabetSorter::reverseString)
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    private static String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}
