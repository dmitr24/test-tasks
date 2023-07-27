package siberteam.testperiod.io.subtask.common.communicator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public abstract class UserCommunicator {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void showFileNameChooseInfo();
    protected abstract void showChooseInvitation();

    public String getFileNameFromUser() {
        showFileNameChooseInfo();
        showChooseInvitation();
        return getNextUserInputOrDefaultFileName();
    }

    public String getValidFileNameFromUser(String dir) {
        showFileNameChooseInfo();
        showChooseInvitation();
        String fileName = getNextUserInputOrDefaultFileName();
        Path path = Paths.get(dir + fileName);
        if (!Files.exists(path)) {
            fileName = retryGetProperFileName(dir);
        }
        return fileName;
    }

    protected String retryGetProperFileName(String dir) {
        boolean isFileNameValid = false;
        String fileName = null;
        while (!isFileNameValid) {
            System.out.print("Such file not found. Please, enter once more: ");
            fileName = getNextUserInputOrDefaultFileName();
            Path pAth = Paths.get(dir + fileName);
            isFileNameValid = Files.exists(pAth);
        }
        return fileName;
    }

    protected String getNextUserInputOrDefaultFileName() {
        String fileName = scanner.nextLine();
        if (fileName.trim().length() == 0) {
            fileName = "example-1.txt";
        }
        return fileName;
    }
}
