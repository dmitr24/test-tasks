package siberteam.testperiod.mt2.third.validator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {
    public boolean validate(String fullFilePath) {
        Path path = Paths.get(fullFilePath);
        return Files.exists(path);
    }
}
