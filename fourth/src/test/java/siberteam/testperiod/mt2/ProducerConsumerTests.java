package siberteam.testperiod.mt2;

import org.junit.jupiter.api.*;
import siberteam.testperiod.mt2.third.DictionaryTask;
import siberteam.testperiod.mt2.third.io.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProducerConsumerTests {
    private final ClassLoader classLoader = getClass().getClassLoader();

    @AfterAll
    void deleteOutputFile() throws IOException {
        Path path = Paths.get(classLoader.getResource("third/output.txt").getPath());
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @RepeatedTest(20)
    @DisplayName("Consumer doesn't have dictionary inconsistency with high load")
    void consumerHaveConsistentDictionaryWithHighLoadTest() throws ExecutionException, InterruptedException {
        String[] args = createArgs("test-urls-big.txt");
        String perfectExample = classLoader.getResource("third/perfect-output-big.txt").getPath();

        DictionaryTask.main(args);

        assertThatDictionariesEquals(perfectExample);
    }

    private String[] createArgs(String urlsFileName) {
        String[] args = new String[4];
        args[0] = "-i";
        args[1] = classLoader.getResource("third/" + urlsFileName).getPath();
        args[2] = "-o";
        args[3] = classLoader.getResource("third/").getPath();
        return args;
    }

    private void assertThatDictionariesEquals(String perfectFileLocation) {
        FileReader perfectFileReader = new FileReader(perfectFileLocation);
        List<String> perfectDictionary = perfectFileReader.getNaturalOrderedWords();
        FileReader outputFileReader =
                new FileReader(classLoader.getResource("third/output.txt").getPath());
        List<String> outputDictionary = outputFileReader.getNaturalOrderedWords();
        Assertions.assertEquals(perfectDictionary, outputDictionary);
    }
}
