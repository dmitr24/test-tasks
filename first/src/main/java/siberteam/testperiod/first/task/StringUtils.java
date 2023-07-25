package siberteam.testperiod.first.task;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringUtils {
    private static final int LETTER_CASE_DISTANCE = 32;

    private StringUtils() {}

    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }
        String[] splitWord = str.split("");
        splitWord[0] = splitWord[0].toUpperCase();
        return String.join("", splitWord);
    }

    public static boolean isPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return false;
        }
        if (str.length() == 1) {
            return true;
        }
        String[] letters = str.split("");
        for (int i = 0; i < letters.length; i++) {
            if (!letters[i].equals(letters[letters.length - i -1])) {
                return false;
            }
        }
        return true;
    }

    public static String alphabetize(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
         List<String> chars =  str.chars()
                 .filter(StringUtils::isLetter)
                 .boxed()
                 .sorted(StringUtils::compareLetters)
                 .map(StringUtils::fromInteger)
                 .collect(Collectors.toList());
        return String.join("", chars);
    }

    private static int compareLetters(int left, int right) {
        if (left < 97) {
            left += LETTER_CASE_DISTANCE;
        }
        if (right < 97) {
            right += LETTER_CASE_DISTANCE;
        }
        return Integer.compare(left, right);
    }

    private static boolean isLetter(int character) {
        if (('a' <= character && character <= 'z') ||
                ('A' <= character && character <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }

    private static String fromInteger(Integer intValue) {
        char charValue = (char) intValue.intValue();
        return String.valueOf(charValue);
    }
}
