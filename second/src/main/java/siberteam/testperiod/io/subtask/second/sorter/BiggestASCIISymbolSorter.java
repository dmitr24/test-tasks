package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@SorterInfo(name = "ASCII biggest letter sorter",
        description = "Sorting words by biggest containing ASCII character")
public class BiggestASCIISymbolSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .sorted(this::compareStrings)
                .collect(Collectors.joining("\n"));
    }

    private int compareStrings(String left, String right) {
        char leftBiggestChar = getBiggestASCIIChar(left);
        char rightBiggestChar = getBiggestASCIIChar(right);
        return Integer.compare(leftBiggestChar, rightBiggestChar);
    }

    private char getBiggestASCIIChar(String str) {
        char[] chars = str.toCharArray();
        char biggestChar = 0;
        for (char aChar : chars) {
            if (aChar > biggestChar) {
                biggestChar = aChar;
            }
        }
        return biggestChar;
    }
}
