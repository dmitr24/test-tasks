package siberteam.testperiod.mt2.second;

import siberteam.testperiod.mt2.second.cli.CliParser;
import siberteam.testperiod.mt2.second.data.UserRequest;
import siberteam.testperiod.mt2.second.factory.SorterFactory;
import siberteam.testperiod.mt2.second.io.FileReader;
import siberteam.testperiod.mt2.second.io.FileWriter;
import siberteam.testperiod.mt2.second.sorter.Sorter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class SortingTask {
    private static final SorterFactory sorterFactory = new SorterFactory();

    public static void main(String[] args) {
        try {
            CliParser parser = new CliParser();
            UserRequest userRequest = parser.parse(args);
            if (userRequest.isHelpRequest()) {
                printHelp();
            } else {
                applySort(userRequest);
            }
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }
    }

    private static void printHelp() {
        System.out.println(">>> Example of valid command to start provided below\n" +
                "java -jar sorter.jar -i input.txt " +
                "-o output-folder " +
                "-s siberteam.testperiod.io.subtask.second.sorter.AlphabetSorter\n");
        System.out.println(">>> Available sorters");
        sorterFactory.getSortersData().forEach(System.out::println);
    }

    private static void applySort(UserRequest userRequest) {
        FileReader reader = new FileReader(userRequest.getFileName());
        Set<String> words = reader.getDistinctWords();
        List<String> dictionary = new ArrayList<>(words);
        if (userRequest.getParallelExecutions() == null) {
            executeSimple(userRequest, dictionary);
        } else {
            executeMultithreading(userRequest, dictionary);
        }
    }

    private static void executeSimple(UserRequest userRequest, List<String> dictionary) {
        List<String> sortedWords = sorterFactory
                .getInstance(userRequest.getSorterName())
                .sort(dictionary);
        FileWriter writer = new FileWriter(userRequest.getOutputDir());
        writer.write(String.join("\n", sortedWords));
    }

    private static void executeMultithreading(UserRequest userRequest, List<String> dictionary) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(userRequest.getParallelExecutions());
        Set<Sorter> allSorters = sorterFactory.getAllInstances();
        List<Future> tasks = new ArrayList<>();
        for (Sorter sorter : allSorters) {
            tasks.add(forkJoinPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " " + sorter.getClass().getSimpleName());
                List<String> dictionaryCopy = new ArrayList<>(dictionary);
                List<String> sortedWords = sorter.sort(dictionaryCopy);
                FileWriter writer = new FileWriter(userRequest.getOutputDir());
                writer.write(sorter.getClass().getSimpleName(), String.join("\n", sortedWords));
            }));
        }
        tasks.forEach(task -> {
            try {
                task.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
