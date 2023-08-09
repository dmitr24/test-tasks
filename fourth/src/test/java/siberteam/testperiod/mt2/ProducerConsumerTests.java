package siberteam.testperiod.mt2;

import org.junit.jupiter.api.*;
import siberteam.testperiod.mt2.third.DictionaryTask;
import siberteam.testperiod.mt2.third.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProducerConsumerTests {
    private final ClassLoader classLoader = getClass().getClassLoader();
    private Method createDictionaryMethod;
    private Method actualSorterMethod;

    @BeforeAll
    void loadProducers() throws NoSuchMethodException {
        createDictionaryMethod = DictionaryTask.class.getDeclaredMethod("createDictionary", Set.class);
        createDictionaryMethod.setAccessible(true);
        actualSorterMethod = DictionaryTask.class.getDeclaredMethod("sortDictionary", Set.class);
        actualSorterMethod.setAccessible(true);
    }

    @RepeatedTest(20)
    @DisplayName("Consumer doesn't have dictionary inconsistency with high load")
    void consumerHaveConsistentDictionaryWithHighLoadTest() throws InvocationTargetException, IllegalAccessException {
        Set<String> urls = new HashSet<>();
        urls.add(classLoader.getResource("third/test-1-big.txt").getPath());
        urls.add(classLoader.getResource("third/test-2-big.txt").getPath());
        urls.add(classLoader.getResource("third/test-3-big.txt").getPath());
        List<String> perfectDictionary =
                getPerfectDictionary(classLoader.getResource("third/perfect-output-big.txt").getPath());

        List<String> result = (List<String>) createDictionaryMethod.invoke(null, urls);

        Assertions.assertEquals(perfectDictionary, result);
    }

    private List<String> getPerfectDictionary(String path) throws InvocationTargetException, IllegalAccessException {
        FileReader fileReader = new FileReader(path);
        Set<String> words = fileReader.getDistinctWords();
        return (List<String>) actualSorterMethod.invoke(null, words);
    }
}
