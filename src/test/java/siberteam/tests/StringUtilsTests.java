package siberteam.tests;

import sbierteam.tests.tak.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.ArrayList;
import java.util.List;

public class StringUtilsTests {
    @DisplayName("Capitalization method tests")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class CapitalizeTests {
        @DisplayName("Default word sets upper case")
        @ParameterizedTest
        @MethodSource("defaultStringFactory")
        void firstLetterIsUpTest(String word) {
            String capitalizedWord = StringUtils.capitalize(word);

            String firstLetterUpperCase = capitalizedWord.split("")[0];
            firstLetterUpperCase =firstLetterUpperCase.toUpperCase();

            Assertions.assertEquals(firstLetterUpperCase, capitalizedWord.split("")[0]);
        }

        @DisplayName("Null word capitalizes to null")
        @ParameterizedTest
        @NullSource
        void nullStringProvidedTest(String nullWord) {
            nullWord = StringUtils.capitalize(nullWord);

            Assertions.assertNull(nullWord);
        }

        @DisplayName("Empty word's first letter capitalizes to empty word")
        @ParameterizedTest
        @EmptySource
        void emptyStringProvidedTest(String emptyWord) {
            emptyWord = StringUtils.capitalize(emptyWord);

            Assertions.assertEquals("", emptyWord);
        }

        @DisplayName("Word with special or numeric first letter doesn't change")
        @ParameterizedTest
        @MethodSource("notDefaultStringFactory")
        void NumericOrSpecialSymbolProvidedTest( String word) {
            String newWord = StringUtils.capitalize(word);

            Assertions.assertEquals(word, newWord);
        }

        @SuppressWarnings("SpellCheckingInspection")
        List<String> defaultStringFactory() {
            List<String> strings = new ArrayList<>();
            strings.add("vanya");
            strings.add("sasha");
            strings.add("Gosha");
            return strings;
        }

        @SuppressWarnings("SpellCheckingInspection")
        List<String> notDefaultStringFactory() {
            List<String> strings = new ArrayList<>();
            strings.add("4vanya");
            strings.add("106sasha");
            strings.add("@Gosha");
            return strings;
        }
    }

    @Nested
    @DisplayName("Palindrome method tests")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class PalindromeTests {
        @DisplayName("Single letter is a palindrome")
        @ParameterizedTest
        @ValueSource(strings = "a")
        void singleLetterWordTest(String string) {
            boolean result = StringUtils.isPalindrome(string);

            Assertions.assertTrue(result);
        }

        @DisplayName("Empty and null words isn't a palindrome")
        @ParameterizedTest
        @NullAndEmptySource
        void emptyWordTest(String string) {
            boolean result = StringUtils.isPalindrome(string);

            Assertions.assertFalse(result);
        }

        @DisplayName("Palindrome is a palindrome")
        @ParameterizedTest
        @MethodSource("palindromeFactory")
        void palindromeTest(String palindrome) {
            boolean result = StringUtils.isPalindrome(palindrome);

            Assertions.assertTrue(result);
        }

        @DisplayName("Not Palindrome isn't a palindrome")
        @ParameterizedTest
        @MethodSource("notPalindromeFactory")
        void notPalindromeTest(String notPalindrome) {
            boolean result = StringUtils.isPalindrome(notPalindrome);

            Assertions.assertFalse(result);
        }

        @SuppressWarnings("SpellCheckingInspection")
        public List<String> palindromeFactory() {
            List<String> strings = new ArrayList<>();
            strings.add("aa4aa");
            strings.add("aa");
            strings.add("abba");
            strings.add("aba");
            strings.add("abccba");
            return strings;
        }

        @SuppressWarnings("SpellCheckingInspection")
        public List<String> notPalindromeFactory() {
            List<String> strings = new ArrayList<>();
            strings.add("aa4aa4");
            strings.add("aa4");
            strings.add("abbab");
            strings.add("abas");
            strings.add("abccbaa");
            return strings;
        }
    }

    @Nested
    @DisplayName("Alphabetize method tests")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
     class AlphabetizeTests {
        @DisplayName("Alphabetize null returns null")
        @ParameterizedTest
        @NullSource
        void nullAlphabetizeTest(String str) {
            str = StringUtils.alphabetize(str);

            Assertions.assertNull(str);
        }

        @DisplayName("Alphabetize empty word returns empty word")
        @ParameterizedTest
        @EmptySource
        void emptyAlphabetizeTest(String str) {
            str = StringUtils.alphabetize(str);

            Assertions.assertEquals("", str);
        }

        @DisplayName("Alphabetize regular word returns correct answer")
        @ParameterizedTest
        @ValueSource(strings = "bacCaA(8$#@klbB)")
        void defaultStringTest(String str) {
            str = StringUtils.alphabetize(str);

            Assertions.assertEquals("aaAbbBcCkl", str);
        }
    }
}
