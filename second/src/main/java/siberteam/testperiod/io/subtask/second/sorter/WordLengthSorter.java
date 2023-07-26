package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.util.TextUtils;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class WordLengthSorter implements Sorter {
    @Override
    public String sort(String text) {
        return TextUtils
                .getNotEmptyWordsFromText(text)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.joining("\n"));
    }
}
