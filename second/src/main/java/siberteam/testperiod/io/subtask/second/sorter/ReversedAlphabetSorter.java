package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.util.TextUtils;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class ReversedAlphabetSorter implements Sorter {
    @Override
    public String sort(String text) {
        return TextUtils
                .getNotEmptyWordsFromText(text)
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
