package siberteam.testperiod.mt2.third;

import siberteam.testperiod.mt2.third.cli.CliParser;
import siberteam.testperiod.mt2.third.data.Locations;
import siberteam.testperiod.mt2.third.io.FileReader;
import siberteam.testperiod.mt2.third.io.FileWriter;
import siberteam.testperiod.mt2.third.sorter.AlphabetSorter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DictionaryTask {
    private static Locations locations;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        locations = getLocations(args);
        Set<String> urls = getUrls(locations.getInputFileLocation());
        List<String> dictionary = createDictionary(urls);
        write(dictionary);
    }

    private static List<String> createDictionary(Set<String> urls) throws ExecutionException, InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10000);
        CompletableFuture<Consumer> consumerFuture = startConsumer(queue);
        CompletableFuture<Void>[] producers = createProducers(urls, queue);
        completeProducing(producers, queue);
        return consumerFuture
                .thenApply(Consumer::getDictionary)
                .thenApplyAsync(DictionaryTask::sortDictionary)
                .get();
    }

    private static void completeProducing(CompletableFuture<Void>[] producers,
                                          BlockingQueue<String> queue) throws ExecutionException, InterruptedException {
        CompletableFuture.allOf(producers).get();
        queue.put("\0");
    }

    private static CompletableFuture<Void>[] createProducers(Set<String> urls,
                                                             BlockingQueue<String> queue) {
        CompletableFuture<Void>[] producers = new CompletableFuture[urls.size()];
        int i = 0;
        for (String url : urls) {
            producers[i] = CompletableFuture.supplyAsync(() -> {
                Producer producer = new Producer(url, queue);
                producer.produce();
                return null;
            });
            i++;
        }
        return producers;
    }

    private static CompletableFuture<Consumer> startConsumer(BlockingQueue<String> queue) {
        return CompletableFuture.supplyAsync(() -> {
            Consumer consumer = new Consumer(queue);
            consumer.consume();
            return consumer;
        });
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

    private static Locations getLocations(String[] args) {
        CliParser parser = new CliParser();
        return parser.parse(args);
    }
}
