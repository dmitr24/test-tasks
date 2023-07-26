package siberteam.testperiod.io.subtask.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public class TextUtils {
    public static List<String> getDistinctLetters(String text) {
        return Stream.of(text.split(""))
                .distinct()
                .collect(Collectors.toList());
    }

    public static Stream<String> getNotEmptyWordsFromText(String text) {
        return Stream.of(text.split("\n| "))
                .filter(str -> str.length() > 0);
    }
}
