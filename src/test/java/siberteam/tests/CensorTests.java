package siberteam.tests;

import org.junit.jupiter.api.BeforeAll;
import sbierteam.tests.task.Censor;
import sbierteam.tests.exception.NullCensoredTextProvidedException;
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
    private static Censor censor;

    @BeforeAll
    static void initCensor() {
        censor = new Censor("randomWord");
    }

    @Test
    @DisplayName("Null censurable word test")
    public void nullCensurableWordProvidedTest() {
        Assertions.assertThrows(NullCensoredTextProvidedException.class,
                () -> new Censor(null));
    }

    @DisplayName("Null text")
    @ParameterizedTest
    @NullSource
    void nullTextProvidedTest(String text) {
        Assertions.assertDoesNotThrow(() -> censor.apply(text));
    }

    @DisplayName("Text doesn't have censored word")
    @ParameterizedTest
    @EmptySource
    @MethodSource("randomWordFactory")
    void deletingCensurableWordTest(String text) {
        String censoredText = censor.apply(text);

        Assertions.assertFalse(censoredText.contains("randomWord"));
    }

    @DisplayName("Replacing censurable word with censored")
    @ParameterizedTest
    @MethodSource("uncensoredStringFactory")
    void censoringTextTest(String text) {

        String censoredText = censor.apply(text);

        Assertions.assertFalse(censoredText.contains("randomWord"));
        Assertions.assertTrue(censoredText.contains("censored"));
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static List<String> randomWordFactory() {
        List<String> texts = new ArrayList<>();
        texts.add("randomwordrondomStrongrandomword4random4wordrandom44word");
        texts.add("randomwordrondomStrongrandomword4rawordwordrandom");
        texts.add("randomWord");
        texts.add("randomword");
        return texts;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static List<String> uncensoredStringFactory() {
        List<String> texts = new ArrayList<>();
        texts.add("randrandomWordomworandomWordrdrondomStrongrrandomwordandomwrandomwordord4random4wordrandom44word");
        texts.add("randomwrandomWordordrondrandrandomWordomwordomStrongrarandomwordndomword4rawordwordrandom");
        texts.add("randomWrandomwrandomWordordord");
        texts.add("randrrandomWordandomwordomword");
        return texts;
    }
}
