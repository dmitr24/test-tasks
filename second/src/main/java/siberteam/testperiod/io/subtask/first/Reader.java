package siberteam.testperiod.io.subtask.first;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Reader {
    private static final String DEFAULT_LOCATION = "/home/dmitryk/projects/main/second/src/main/resources/first/";

    public static String readFromFile(String fileName) {
        Path path = Paths.get(DEFAULT_LOCATION + fileName);
        String result = null;
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
            result = lines.collect(Collectors.joining());
        } catch (IOException exception) {
            System.out.println("Unable to read this file");
            System.exit(1);
        }
        return result;
    }
}
