package siberteam.testperiod.io.subtask.common.data;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Text {
    private final String content;

    public List<String> getDistinctLetters() {
        return Stream.of(content.split(""))
                .distinct()
                .collect(Collectors.toList());
    }

    public Stream<String> getNotEmptyWords() {
        return Stream.of(content.split("[\n ]"))
                .filter(str -> str.length() > 0);
    }
}
