package org.example.tak;


import java.util.Arrays;
import java.util.Objects;

public class StringUtils {

    public static String capitalize(String str) {
        if (str == null) {
            return null;
        }

        String[] splitWord = str.split("");

        String firstLetter = splitWord[0];
        firstLetter = firstLetter.toUpperCase();

        splitWord[0] = firstLetter;

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

    /// todo
    public static String alphabetize(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
        str = str.replaceAll("[^A-Za-z]", "");

        char[] chars = str.toCharArray();



        return String.copyValueOf(chars);
    }
}
