package siberteam.testperiod.io.subtask.first.io;

import lombok.RequiredArgsConstructor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class Reader {
    private final String path;

    public void readToBuilder(Consumer<String> buildMethod) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String newLine = reader.readLine();
            while (newLine != null) {
                buildMethod.accept(newLine);
                newLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
