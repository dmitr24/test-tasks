package siberteam.testperiod.io.subtask.second.cli;

import org.apache.commons.cli.*;
import siberteam.testperiod.io.subtask.second.data.SortRequest;

public class CliParser {
    private final Option fileNameOption;
    private final Option outputDirectoryOption;
    private final Option sorterNameOption;
    private final Option helpOption;
    private final Options options;

    public CliParser() {
        fileNameOption = new Option("i", true, "FileName for parsing");
        outputDirectoryOption = new Option("o", true, "Directory which contains file");
        sorterNameOption = new Option("s", true, "Sorting class");
        helpOption = new Option("h", "help", false, "Info about sorters");
        options = new Options();
        options.addOption(fileNameOption);
        options.addOption(outputDirectoryOption);
        options.addOption(sorterNameOption);
        options.addOption(helpOption);
    }

    public SortRequest parse(String[] args) throws ParseException {
        SortRequest sortInfo = new SortRequest();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);
        handleHelp(commandLine);
        sortInfo.setFileName(getFileName(commandLine));
        sortInfo.setOutputDir(getDirectoryName(commandLine));
        sortInfo.setSorterName(getSorterName(commandLine));
        return sortInfo;
    }

    private String getFileName(CommandLine commandLine) {
        if (!commandLine.hasOption(fileNameOption)) {
            System.err.println("-i opt is required. See -h or -help for all necessary information.");
            System.exit(1);
        }
        return commandLine.getOptionValue(fileNameOption);
    }

    private String getDirectoryName(CommandLine commandLine) {
        if (!commandLine.hasOption(outputDirectoryOption)) {
            System.err.println("-o opt is required. See -h or -help for all necessary information.");
            System.exit(1);
        }
        return commandLine.getOptionValue(outputDirectoryOption);
    }

    private String getSorterName(CommandLine commandLine) {
        if (!commandLine.hasOption(sorterNameOption)) {
            System.err.println("-s opt is required. See -h or -help for all necessary information.");
            System.exit(1);
        }
        return commandLine.getOptionValue(sorterNameOption);
    }

    private void handleHelp(CommandLine commandLine) {
        if (commandLine.hasOption(helpOption)) {
            System.out.println(">>> Example of valid command to start provided below\n" +
                    "java -jar sorter.jar -i input.txt " +
                    "-o output-folder/ " +
                    "-s siberteam.testperiod.io.subtask.second.sorter.AlphabetSorter\n");
            System.out.println(">>> Available sorters");
            System.out.println("--- Alphabet sort - " +
                    "siberteam.testperiod.io.subtask.second.sorter.AlphabetSorter");
            System.out.println("--- Reverse alphabet sort - " +
                    "siberteam.testperiod.io.subtask.second.sorter.ReversedAlphabetSorter");
            System.out.println("--- Word length sort - " +
                    "siberteam.testperiod.io.subtask.second.sorter.WordLengthSorter ");
            System.out.println("--- Count of unique symbols sort - " +
                    "siberteam.testperiod.io.subtask.second.sorter.UniqueLettersCountsSorter");
            System.out.println("--- ASCII symbol value sort - " +
                    "siberteam.testperiod.io.subtask.second.sorter.BiggestASCIISymbolSorter");
            System.exit(0);
        }
    }
}