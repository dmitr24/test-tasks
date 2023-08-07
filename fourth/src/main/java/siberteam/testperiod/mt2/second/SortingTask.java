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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        ExecutorService executorService =
                Executors.newFixedThreadPool(userRequest.getParallelExecutions());
        Set<Sorter> allSorters = sorterFactory.getAllInstances();
        for (Sorter sorter : allSorters) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                List<String> sortedWords = sorter.sort(dictionary);
                FileWriter writer = new FileWriter(userRequest.getOutputDir());
                writer.write(sorter.getClass().getSimpleName(), String.join("\n", sortedWords));
            });
        }
        executorService.shutdown();
    }
}
