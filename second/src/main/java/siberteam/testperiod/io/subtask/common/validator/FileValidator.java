package siberteam.testperiod.io.subtask.common.validator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {
    public boolean validate(String dir, String fileName) {
        Path path = Paths.get(dir + fileName);
        return Files.exists(path);
    }

    public boolean validate(String fullFilePath) {
        Path path = Paths.get(fullFilePath);
        return Files.exists(path);
    }
}
