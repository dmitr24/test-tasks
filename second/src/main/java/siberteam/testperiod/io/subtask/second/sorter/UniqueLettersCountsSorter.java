package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.util.TextUtils;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class RepeatedLettersCountsSorter implements Sorter {
    @Override
    public String sort(String text) {
        return TextUtils
                .getNotEmptyWordsFromText(text)
                .sorted(Comparator.comparingInt(value -> TextUtils.getDistinctLetters(value).size()))
                .collect(Collectors.joining("\n"));
    }
}
