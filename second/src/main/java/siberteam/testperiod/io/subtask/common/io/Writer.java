package siberteam.testperiod.io.subtask.general.io;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Writer {
    private static String location = "";

    public static void write(String text) throws IOException {
        Path filePath = getAcceptableFileName();
        writeToParticularFile(filePath, text);
    }

    private static void writeToParticularFile(Path filePath, String text) throws IOException {
        try {
            Files.createFile(filePath);
            if (Files.isWritable(filePath)) {
                Files.write(filePath, text.getBytes());
            } else {
                throw new IOException();
            }
        } catch (IOException exception) {
            throw new IOException("Unable to write to the file with path " + filePath);
        }
    }

    private static Path getAcceptableFileName() {
        Path path = null;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            String fileName = location + "output-" + i + ".txt";
            path = Paths.get(fileName);
            if (!Files.exists(path)) {
                break;
            }
        }
        return path;
    }

    public static void setLocation(String location) {
        Writer.location = location;
    }
}
