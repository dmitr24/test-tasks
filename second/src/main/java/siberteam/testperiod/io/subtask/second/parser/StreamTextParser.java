package siberteam.testperiod.io.subtask.second.parser;

import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class StreamTextParser {
    private final Stream<String> lines;

    public List<String> getDictionary() {
        return lines
                .map(line -> Arrays.asList(line.split(" ")))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
