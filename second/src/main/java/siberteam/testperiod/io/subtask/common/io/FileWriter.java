package siberteam.testperiod.io.subtask.common.io;

import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class FileWriter {
    private final String location;

    public void write(String text) throws IOException {
        Path filePath = Paths.get(location + "/output.txt");
        prepareFile(filePath);
        writeToParticularFile(filePath, text);
    }

    private void writeToParticularFile(Path filePath, String text) throws IOException {
        try {
            if (Files.isWritable(filePath)) {
                Files.write(filePath, text.getBytes());
            } else {
                throw new IOException();
            }
        } catch (IOException exception) {
            throw new IOException("Unable to write to the file with path " + filePath);
        }
    }

    private void prepareFile(Path filePath) throws IOException {
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
        Files.createFile(filePath);
    }
}
