package siberteam.testperiod.first.task;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StringUtils {
    private static final int LETTER_CASE_DISTANCE = 32;

    private StringUtils() {}

    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        if (0 < chars.length && 'a' < chars[0] && chars[0] < 'z') {
            chars[0] += LETTER_CASE_DISTANCE;
        }
        return String.copyValueOf(chars);
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
         return str.chars()
                 .filter(StringUtils::isLetter)
                 .mapToObj(intVal -> (char) intVal)
                 .sorted(StringUtils::compareLetters)
                 .collect(Collector.of(
                         StringBuilder::new,
                         StringBuilder::append,
                         StringBuilder::append,
                         StringBuilder::toString)
                 );
    }

    private static int compareLetters(char left, char right) {
        if (left < 97) {
            left += LETTER_CASE_DISTANCE;
        }
        if (right < 97) {
            right += LETTER_CASE_DISTANCE;
        }
        return Character.compare(left, right);
    }

    private static boolean isLetter(int character) {
        if (('a' <= character && character <= 'z') ||
                ('A' <= character && character <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }
}
