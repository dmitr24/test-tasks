package siberteam.testperiod.io.subtask.second.cli;

import org.apache.commons.cli.*;
import siberteam.testperiod.io.subtask.second.data.request.HelpRequest;
import siberteam.testperiod.io.subtask.second.data.request.SortRequest;
import siberteam.testperiod.io.subtask.second.data.request.UserRequest;
import siberteam.testperiod.io.subtask.second.exception.ParserException;

public class CliParser {
    private final Option fileNameOption;
    private final Option outputDirectoryOption;
    private final Option sorterNameOption;
    private final Option helpOption;
    private final Options options;
    private CommandLine commandLine;

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

    public UserRequest parse(String[] args) throws ParserException {
        initCommandLine(args);
        if (containsHelpOption()) {
            return new HelpRequest();
        } else {
            return getSortRequest();
        }
    }

    private void initCommandLine(String[] args) throws ParserException {
        try {
            CommandLineParser parser = new DefaultParser();
            commandLine = parser.parse(options, args);
        } catch (ParseException exception) {
            throw new ParserException("Parser exception occurred. opts don't fit restrictions." +
                    " Message: " + exception.getMessage());
        }
    }

    private SortRequest getSortRequest() throws ParserException {
        SortRequest sortRequest = new SortRequest();
        sortRequest.setFileName(getFileName());
        sortRequest.setOutputDir(getDirectoryName());
        sortRequest.setSorterName(getSorterName());
        return sortRequest;
    }

    private String getFileName() throws ParserException {
        if (!commandLine.hasOption(fileNameOption)) {
            throw new ParserException("-i opt is required. See -h or -help for all necessary information.");
        }
        return commandLine.getOptionValue(fileNameOption);
    }

    private String getDirectoryName() throws ParserException {
        if (!commandLine.hasOption(outputDirectoryOption)) {
            throw new ParserException("-o opt is required. See -h or -help for all necessary information.");
        }
        return commandLine.getOptionValue(outputDirectoryOption);
    }

    private String getSorterName() throws ParserException {
        if (!commandLine.hasOption(sorterNameOption)) {
            throw new ParserException("-s opt is required. See -h or -help for all necessary information.");
        }
        return commandLine.getOptionValue(sorterNameOption);
    }

    private boolean containsHelpOption() {
        return commandLine.hasOption(helpOption);
    }
}
