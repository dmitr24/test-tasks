package siberteam.testperiod.mt2.third.io;

import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class FileWriter {
    private final String location;

    public void write(String text) {
        Path filePath = Paths.get(location + "/output.txt");
        prepareFile(filePath);
        writeToParticularFile(filePath, text);
    }

    private void writeToParticularFile(Path filePath, String text) {
        try {
            if (Files.isWritable(filePath)) {
                Files.write(filePath, text.getBytes());
            } else {
                throw new IOException();
            }
        } catch (IOException exception) {
            throw new RuntimeException("Unable to write to the file with path " + filePath);
        }
    }

    private void prepareFile(Path filePath) {
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.createFile(filePath);
        } catch (IOException exception) {
            throw new RuntimeException("File preparation exception: " + exception.getMessage());
        }
    }
}
