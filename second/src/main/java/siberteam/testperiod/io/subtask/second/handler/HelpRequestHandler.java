package siberteam.testperiod.io.subtask.second.handler;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.second.factory.SorterFactory;

@RequiredArgsConstructor
public class HelpRequestHandler {
    private final SorterFactory sorterFactory;

    public void handle() {
        System.out.println(">>> Example of valid command to start provided below\n" +
                "java -jar sorter.jar -i input.txt " +
                "-o output-folder " +
                "-s siberteam.testperiod.io.subtask.second.sorter.AlphabetSorter\n");
        System.out.println(">>> Available sorters");
        sorterFactory.getSortersData().forEach(System.out::println);
    }
}
