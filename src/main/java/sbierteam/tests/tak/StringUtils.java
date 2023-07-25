package sbierteam.tests.tak;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringUtils {
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
                 .sorted(StringUtils::compare)
                 .map(StringUtils::fromInteger)
                 .collect(Collectors.toList());
        return String.join("", chars);
    }

    private static int compare(int left, int right) {
        if (left < 97) {
            left += 32;
        }
        if (right < 97) {
            right += 32;
        }
        return Integer.compare(left, right);
    }

    private static boolean isLetter(int character) {
        if ((96 < character && character < 123) ||
                (64 < character && character < 91)) {
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
