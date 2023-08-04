package siberteam.testperiod.io.subtask.second.io;

import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private final String path;

    public FileReader(String path) {
        FileValidator validator = new FileValidator();
        if (!validator.validate(path)) {
            throw new RuntimeException("File with such name not found");
        }
        this.path = path;
    }

    public List<String> getDistinctWords() {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            List<String> words = new ArrayList<>();
            int newChar = reader.read();
            StringBuilder wordBuilder = new StringBuilder();
            while (newChar != -1) {
                if (newChar != '\n' && newChar != ' ') {
                    wordBuilder.append((char) newChar);
                } else {
                    if (wordBuilder.length() > 0 && !words.contains(wordBuilder.toString())) {
                        String word = wordBuilder.toString();
                        words.add(word);
                    }
                    wordBuilder = new StringBuilder();
                }
                newChar =  reader.read();
            }
            if (wordBuilder.length() > 0 && !words.contains(wordBuilder.toString())) {
                words.add(wordBuilder.toString());
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading from file: " + path);
        }
    }
}
