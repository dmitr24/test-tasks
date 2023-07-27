package siberteam.testperiod.io.subtask.first.communicator;

import siberteam.testperiod.io.subtask.common.communicator.UserCommunicator;
import siberteam.testperiod.io.subtask.common.io.ColorizedConsolePrinter;

public class FirstTaskUserCommunicator extends UserCommunicator {
    @Override
    public void showFileNameChooseInfo() {
        ColorizedConsolePrinter consolePrinter = new ColorizedConsolePrinter();
        consolePrinter.printLnGreen("If trimmed string doesn't have signs," +
                " filename automatically sets to example-1.txt\n");
    }

    @Override
    protected void showChooseInvitation() {
        System.out.print("Please, enter name of file which you want to analyze (e.g. example-2.txt): ");
    }
}
