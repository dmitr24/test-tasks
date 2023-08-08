package siberteam.testperiod.mt2.third.io;

import siberteam.testperiod.mt2.third.validator.FileValidator;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            Set<String> words = new HashSet<>();
            int newChar = reader.read();
            StringBuilder wordBuilder = new StringBuilder();
            while (newChar != -1) {
                if (newChar != '\n' && newChar != ' ') {
                    wordBuilder.append((char) newChar);
                } else {
                    if (wordBuilder.length() >= 0) {
                        String word = wordBuilder.toString();
                        words.add(word);
                    }
                    wordBuilder = new StringBuilder();
                }
                newChar =  reader.read();
            }
            if (wordBuilder.length() >= 0) {
                words.add(wordBuilder.toString());
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading from file: " + path);
        }
    }

    public void writeRussianWordsToDictionary(Set<String> dictionary) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {
            int newChar = reader.read();
            StringBuilder wordBuilder = new StringBuilder();
            while (newChar != -1) {
                if ((newChar >= 'а' && newChar <= 'я') ||
                        (newChar >= 'А' && newChar <= 'Я')) {
                    wordBuilder.append((char) newChar);
                } else {
                    if (wordBuilder.length() >= 3) {
                        String word = wordBuilder.toString();
                        dictionary.add(word);
                    }
                    wordBuilder = new StringBuilder();
                }
                newChar =  reader.read();
            }
            if (wordBuilder.length() >= 3) {
                dictionary.add(wordBuilder.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading from file: " + path);
        }
    }
}
