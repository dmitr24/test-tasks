package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.util.TextUtils;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class BiggestASCIISymbolSorter implements Sorter {
    @Override
    public String sort(String text) {
        return TextUtils
                .getNotEmptyWordsFromText(text)
                .sorted(BiggestASCIISymbolSorter::compareStrings)
                .collect(Collectors.joining("\n"));
    }

    private static int compareStrings(String left, String right) {
        char leftBiggestChar = getBiggestASCIIChar(left);
        char rightBiggestChar = getBiggestASCIIChar(right);
        return Integer.compare(leftBiggestChar, rightBiggestChar);
    }

    private static char getBiggestASCIIChar(String str) {
        char[] chars = str.toCharArray();
        char biggestChar = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > biggestChar) {
                biggestChar = chars[i];
            }
        }
        return biggestChar;
    }
}
