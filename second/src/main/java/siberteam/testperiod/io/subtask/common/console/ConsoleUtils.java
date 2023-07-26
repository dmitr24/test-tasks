package siberteam.testperiod.io.subtask.common.console;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ConsoleUtils {
    private static final String TEXT_COLOR_GREEN = "\u001B[32m";
    private static final String TEXT_COLOR_DEFAULT = "\u001B[0m";

    public static void printLnGreen(String str) {
        System.out.println(ConsoleUtils.TEXT_COLOR_GREEN + str + ConsoleUtils.TEXT_COLOR_DEFAULT);
    }
}
