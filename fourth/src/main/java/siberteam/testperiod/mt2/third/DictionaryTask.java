package siberteam.testperiod.mt2.third;

import siberteam.testperiod.mt2.third.cli.CliParser;
import siberteam.testperiod.mt2.third.data.Locations;
import siberteam.testperiod.mt2.third.io.FileReader;
import siberteam.testperiod.mt2.third.io.FileWriter;
import siberteam.testperiod.mt2.third.sorter.AlphabetSorter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class DictionaryTask {
    private static Locations locations;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        locations = getLocations(args);
        Set<String> urls = getUrls(locations.getInputFileLocation());
        createDictionary(urls);
    }

    private static void createDictionary(Set<String> urls) throws ExecutionException, InterruptedException {
        Set<String> words = ConcurrentHashMap.newKeySet();
        CompletableFuture<Void>[] dictionaries = new CompletableFuture[urls.size()];
        int i = 0;
        for (String url : urls) {
            dictionaries[i] = CompletableFuture.supplyAsync(() -> {
                Set<String> newWords = getRussianWords(url);
                words.addAll(newWords);
                return null;
            });
            i++;
        }
        CompletableFuture
                .allOf(dictionaries)
                .thenApply((nth) -> sortDictionary(words))
                .thenAcceptAsync(DictionaryTask::write)
                .get();
    }

    private static void write(List<String> dictionary) {
        FileWriter writer = new FileWriter(locations.getOutputDirectoryLocation());
        writer.write(String.join("\n", dictionary));
    }

    private static List<String> sortDictionary(Set<String> words) {
        List<String> dictionary = new ArrayList<>(words);
        AlphabetSorter sorter = new AlphabetSorter();
        return sorter.sort(dictionary);
    }

    private static Set<String> getUrls(String fileName) {
        FileReader reader = new FileReader(fileName);
        return reader.getDistinctWords();
    }

    private static Set<String> getRussianWords(String fileName) {
        FileReader fileReader = new FileReader(fileName);
        return fileReader.getDistinctRussianWords();
    }

    private static Locations getLocations(String[] args) {
        CliParser parser = new CliParser();
        return parser.parse(args);
    }
}
