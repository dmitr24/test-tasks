package siberteam.testperiod.io.subtask.second.communicator;

import siberteam.testperiod.io.subtask.common.communicator.UserCommunicator;
import siberteam.testperiod.io.subtask.common.io.ColorizedConsolePrinter;

public class SecondTaskUserCommunicator extends UserCommunicator {
    @Override
    public void showFileNameChooseInfo() {
        ColorizedConsolePrinter consolePrinter = new ColorizedConsolePrinter();
        consolePrinter.printLnGreen("If trimmed string doesn't have signs," +
                " filename automatically sets to example-1.txt for faster checkup\n");
    }

    @Override
    protected void showChooseInvitation() {
        System.out.print("Please, enter name of file which you want to sort (e.g. example-2.txt): ");
    }

    public String getSorterClassNameFromUser() {
        System.out.print("Please, enter full qualified classname of sorter that you chose: ");
        return scanner.nextLine();
    }

    private void printSorters() {

    }
}
