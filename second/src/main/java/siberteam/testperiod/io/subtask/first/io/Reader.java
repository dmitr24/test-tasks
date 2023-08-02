package siberteam.testperiod.io.subtask.first.io;

import lombok.RequiredArgsConstructor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class Reader {
    private final String path;

    public void readToBuilder(Consumer<Character> buildMethod) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int newChar = reader.read();
            while (newChar != -1) {
                if (newChar != '\n') {
                    buildMethod.accept((char) newChar);
                }
                newChar =  reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
