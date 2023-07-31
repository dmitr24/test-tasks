package siberteam.testperiod.io.subtask.second.parser;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class StreamTextParser {
    private final Stream<String> lines;

    /// todo
    public List<String> getDictionary() {
        return lines
                .map(line -> Arrays.asList(line.split(" ")))
                .distinct()
                .collect(Collectors.toList());
    }
}
