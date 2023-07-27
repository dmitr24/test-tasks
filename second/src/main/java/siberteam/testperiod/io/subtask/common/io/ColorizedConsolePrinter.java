package siberteam.testperiod.io.subtask.common.io;

public class ColorizedConsolePrinter {
    private static final String TEXT_COLOR_GREEN = "\u001B[32m";
    private static final String TEXT_COLOR_DEFAULT = "\u001B[0m";

    public void printLnGreen(String str) {
        System.out.println(ColorizedConsolePrinter.TEXT_COLOR_GREEN + str + ColorizedConsolePrinter.TEXT_COLOR_DEFAULT);
    }
}
