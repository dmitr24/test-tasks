package siberteam.testperiod.io.subtask.second;

import siberteam.testperiod.io.subtask.second.cli.CliParser;
import siberteam.testperiod.io.subtask.second.data.UserRequest;
import siberteam.testperiod.io.subtask.second.factory.SorterFactory;
import siberteam.testperiod.io.subtask.second.io.FileReader;
import siberteam.testperiod.io.subtask.second.io.FileWriter;
import java.util.List;

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
        List<String> words = reader.getDistinctWords();
        List<String> sortedWords = sorterFactory
                .getInstance(userRequest.getSorterName())
                .sort(words);
        FileWriter writer = new FileWriter(userRequest.getOutputDir());
        writer.write(String.join("\n", sortedWords));
    }
}
