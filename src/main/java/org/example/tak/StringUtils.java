package org.example.tak;


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

    public static String alphabetize(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
        str = str.replaceAll("[^A-Za-z]", "");

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                char tempI = chars[j];
                char tempJ = chars[j + 1];
                if (chars[i] > 97 ) {
                    tempI = (char) (tempI - 32);
                }

                if (chars[i] > 97 ) {
                    tempJ = (char) (tempI - 32);
                }

                if (tempI > tempJ) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return String.copyValueOf(chars);
    }
}
