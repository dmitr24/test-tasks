package siberteam.testperiod.io.subtask.second.io;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.second.exception.WriterException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class FileWriter {
    private final String location;

    public void write(String text) throws WriterException {
        Path filePath = Paths.get(location + "/output.txt");
        prepareFile(filePath);
        writeToParticularFile(filePath, text);
    }

    private void writeToParticularFile(Path filePath, String text) throws WriterException {
        try {
            if (Files.isWritable(filePath)) {
                Files.write(filePath, text.getBytes());
            } else {
                throw new IOException();
            }
        } catch (IOException exception) {
            throw new WriterException("Unable to write to the file with path " + filePath);
        }
    }

    private void prepareFile(Path filePath) throws WriterException {
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.createFile(filePath);
        } catch (IOException exception) {
            throw new WriterException("File preparation exception: " + exception.getMessage());
        }
    }
}
