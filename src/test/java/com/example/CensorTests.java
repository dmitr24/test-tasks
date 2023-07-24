package com.example;

import org.example.tak.Censor;
import org.example.exception.NullCensoredTextProvidedException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;

public class CensorTests {

    @Test
    @DisplayName("Null censurable word test")
    public void nullCensurableWordProvidedTest() {
        Assertions.assertThrows(NullCensoredTextProvidedException.class,
                () -> new Censor(null));
    }

    @DisplayName("Null text")
    @ParameterizedTest
    @NullSource
    public void nullTextProvidedTest(String text) {
        Censor censor =  new Censor("randomword");

        Assertions.assertDoesNotThrow(() -> censor.apply(text));
    }

    @DisplayName("Text doesn't have censored word")
    @ParameterizedTest
    @EmptySource
    @MethodSource("randomWordFactory")
    public void deletingCensurableWordTest(String text) {
        Censor censor =  new Censor("randomword");

        String censoredText = censor.apply(text);

        Assertions.assertFalse(censoredText.contains("randomword"));
    }

    @DisplayName("Replacing censurable word with censored")
    @ParameterizedTest
    @MethodSource("uncensoredStringFactory")
    public void censoringTextTest(String text) {
        Censor censor =  new Censor("randomword");

        String censoredText = censor.apply(text);

        Assertions.assertFalse(censoredText.contains("randomword"));
        Assertions.assertTrue(censoredText.contains("censored"));
    }

    public static List<String> randomWordFactory() {
        List<String> texts = new ArrayList<>();
        texts.add("randomwordrondomStrongrandomword4random4wordrandom44word");
        texts.add("randomwordrondomStrongrandomword4rawordwordrandom");
        texts.add("randomWord");
        texts.add("randomword");
        return texts;
    }

    public static List<String> uncensoredStringFactory() {
        List<String> texts = new ArrayList<>();
        texts.add("randomwordrondomStrongrrandomwordandomwrandomwordord4random4wordrandom44word");
        texts.add("randomwordrondrandomwordomStrongrarandomwordndomword4rawordwordrandom");
        texts.add("randomWrandomwordord");
        texts.add("randrandomwordomword");
        return texts;
    }
}
