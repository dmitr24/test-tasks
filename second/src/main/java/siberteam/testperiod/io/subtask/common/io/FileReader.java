package siberteam.testperiod.io.subtask.common.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private final Path path;

    public FileReader(Path path) throws IOException {
        if (!Files.isReadable(path)) {
            throw new IOException("File with path " + path + " is not readable");
        }
        this.path = path;
    }

    public String readWholeFile() throws IOException {
        String result;
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            result = lines.collect(Collectors.joining("\n"));
        } catch (IOException exception) {
            throw new IOException("Unable to read the file with path " + path);
        }
        return result;
    }

    public Stream<String> getNotClosedLinesStream() throws IOException {
        try {
            return Files.lines(path, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IOException("Unable to get lines from file with path " + path);
        }
    }

    public long getLinesCount() throws IOException {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.filter(line -> line.trim().length() > 0).count();
        } catch (IOException exception) {
            throw new IOException("Unable to get lines count from file with path " + path);
        }
    }
}
