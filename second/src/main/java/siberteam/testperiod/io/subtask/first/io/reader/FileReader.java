package siberteam.testperiod.io.subtask.first.io.reader;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.first.exception.ReaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class FileReader implements Reader {
    private final String path;

    public void readToBuffer(Consumer<Character> buffer) throws ReaderException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            int newChar = reader.read();
            while (newChar != -1) {
                if (newChar != '\n') {
                    buffer.accept((char) newChar);
                }
                newChar =  reader.read();
            }
        } catch (IOException e) {
            throw new ReaderException("Exception while reading from file: " + path);
        }
    }
}
