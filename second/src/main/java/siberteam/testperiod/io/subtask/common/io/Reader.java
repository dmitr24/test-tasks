package siberteam.testperiod.io.subtask.common.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    private static String location = "";

    public static String readFromFile(String fileName) throws IOException {
        Path path = Paths.get(location + fileName);
        String result;
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            result = lines.collect(Collectors.joining("\n"));
        } catch (IOException exception) {
            throw new IOException("Unable to read the file with path " + path);
        }
        return result;
    }

    public static void setLocation(String location) {
        Reader.location = location;
    }
}
