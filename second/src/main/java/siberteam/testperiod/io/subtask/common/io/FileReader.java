package siberteam.testperiod.io.subtask.common.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileReader {
    private final Path path;

    public FileReader(Path path) throws IOException {
        if (!Files.isReadable(path)) {
            throw new IOException("File with path " + path + " is not readable");
        }
        this.path = path;
    }

    public Stream<String> getNotClosedLinesStream() throws IOException {
        try {
            return Files.lines(path, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new IOException("Unable to get lines from file with path " + path);
        }
    }
}
