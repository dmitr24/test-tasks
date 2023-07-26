package siberteam.testperiod.io.subtask.first.util;

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
}
