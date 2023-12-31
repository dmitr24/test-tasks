package siberteam.testperiod.first.task;

import java.util.Objects;
import java.util.stream.Collector;

public class StringUtils {
    private StringUtils() {}

    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }
        char[] chars = str.toCharArray();
        if (0 < chars.length && 'a' <= chars[0] && chars[0] <= 'z') {
            chars[0] = Character.toUpperCase(chars[0]);
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
        char[] letters = str.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != letters[letters.length - i -1]) {
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
        if (left < 'a') {
            left = Character.toLowerCase(left);
        }
        if (right < 'a') {
            right = Character.toLowerCase(right);
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
