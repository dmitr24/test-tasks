package siberteam.testperiod.io.subtask.common.communicator;

import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import java.util.Scanner;

public abstract class UserCommunicator {
    protected Scanner scanner = new Scanner(System.in);

    public abstract void showFileNameChooseInfo();
    protected abstract void showChooseInvitation();

    public String getValidFileNameFromUser(String dir) {
        showFileNameChooseInfo();
        showChooseInvitation();
        String fileName = getNextUserInputOrDefaultFileName();
        FileValidator validator = new FileValidator();
        while (!validator.validate(dir, fileName)) {
            System.out.print("Such file not found. Please, enter once more: ");
            fileName = getNextUserInputOrDefaultFileName();
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
