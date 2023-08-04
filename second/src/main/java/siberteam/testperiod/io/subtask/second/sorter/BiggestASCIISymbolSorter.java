package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;

import java.util.List;

@SorterInfo(name = "ASCII biggest letter sorter",
        description = "Sorting words by biggest containing ASCII character")
public class BiggestASCIISymbolSorter implements Sorter {
    @Override
    public List<String> sort(List<String> words) {
        words.sort(this::compareStrings);
        return words;
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
