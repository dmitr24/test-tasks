package siberteam.testperiod.io.subtask.common.data;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class Text {
    private final String content;

    public Text(List<String> lines) {
        this.content = String.join("\n", lines);
    }

    public List<Character> getDistinctLetters() {
        return Stream.of(content.split(""))
                .map(stringLetter -> {
                    try {
                        return stringLetter.charAt(0);
                    } catch (IndexOutOfBoundsException exception) {
                        return null;
                    }
                })
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Character> getDistinctLettersWithoutStream() {
        char[] chars = content.toCharArray();
        List<Character> distinctLetters = new ArrayList<>();
        for (char character : chars) {
            if (!distinctLetters.contains(character)) {
                distinctLetters.add(character);
            }
        }
        return distinctLetters;
    }

    public Stream<String> getNotEmptyWords() {
        return Stream.of(content.split("[\n ]"))
                .filter(str -> str.length() > 0);
    }
}
