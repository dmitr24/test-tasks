package siberteam.testperiod.mt2.third.io;

import siberteam.testperiod.mt2.third.validator.FileValidator;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class FileReader {
    private final String path;

    public FileReader(String path) {
        FileValidator validator = new FileValidator();
        if (!validator.validate(path)) {
            throw new RuntimeException("File with such name not found");
        }
        this.path = path;
    }

    public Set<String> getDistinctWords() {
        Set<String> words = new HashSet<>();
        writeToSet(words);
        return words;
    }

    public List<String> getNaturalOrderedWords() {
        List<String> words = new ArrayList<>();
        writeToSet(words);
        return words;
    }

    private void writeToSet(Collection<String> words) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            int newChar = reader.read();
            StringBuilder wordBuilder = new StringBuilder();
            while (newChar != -1) {
                if (newChar != '\n' && newChar != ' ' && newChar != ',') {
                    wordBuilder.append((char) newChar);
                } else {
                    if (wordBuilder.length() >= 1) {
                        String word = wordBuilder.toString();
                        words.add(word);
                    }
                    wordBuilder = new StringBuilder();
                }
                newChar =  reader.read();
            }
            if (wordBuilder.length() >= 1) {
                words.add(wordBuilder.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading from file: " + path);
        }
    }
}
