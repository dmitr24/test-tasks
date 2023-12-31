package siberteam.testperiod.mt2.second.cli;

import org.apache.commons.cli.*;
import siberteam.testperiod.mt2.second.data.UserRequest;

public class CliParser {
    private final Option fileNameOption = new Option("i", true, "FileName for parsing");
    private final Option outputDirectoryOption = new Option("o", true, "Directory which contains file");
    private final Option sorterNameOption = new Option("s", true, "Sorting class");
    private final Option helpOption = new Option("h", "help", false, "Info about sorters");
    private final Option paralellOption = Option
            .builder()
            .option("all")
            .hasArg()
            .optionalArg(true)
            .desc("All sorters apply mod")
            .build();

    private CommandLine commandLine;

    private Options getOptionsList() {
        Options options = new Options();
        options.addOption(fileNameOption);
        options.addOption(outputDirectoryOption);
        options.addOption(sorterNameOption);
        options.addOption(helpOption);
        options.addOption(paralellOption);
        return options;
    }

    public UserRequest parse(String[] args) {
        initCommandLine(args);
        return getRequest();
    }

    private void initCommandLine(String[] args) {
        try {
            Options options = getOptionsList();
            CommandLineParser parser = new DefaultParser();
            commandLine = parser.parse(options, args);
        } catch (ParseException exception) {
            throw new RuntimeException("Parser exception occurred. opts don't fit restrictions." +
                    " Message: " + exception.getMessage());
        }
    }

    private UserRequest getRequest() {
        UserRequest userRequest = new UserRequest();
        if (containsHelpOption()) {
            userRequest.setHelpRequest(true);
        } else {
            userRequest.setHelpRequest(false);
            userRequest.setFileName(getFileName());
            userRequest.setOutputDir(getDirectoryName());
            userRequest.setParallelExecutions(getParallelExecutions());
            if (userRequest.getParallelExecutions() == null) {
                userRequest.setSorterName(getSorterName());
            }
        }
        return userRequest;
    }

    private Integer getParallelExecutions() {
        if (!commandLine.hasOption(paralellOption)) {
            return null;
        }
        if (commandLine.getOptionValue(paralellOption) == null) {
            return 2;
        }
        int param = Integer.parseInt(commandLine.getOptionValue(paralellOption));
        if (param < 1) {
            throw new RuntimeException("-all param have to be bigger then 0");
        }
        return param;
    }

    private String getFileName() {
        if (!commandLine.hasOption(fileNameOption)) {
            throw new RuntimeException("-i opt is required. See -h or -help for all necessary information.");
        }
        return commandLine.getOptionValue(fileNameOption);
    }

    private String getDirectoryName() {
        if (!commandLine.hasOption(outputDirectoryOption)) {
            throw new RuntimeException("-o opt is required. See -h or -help for all necessary information.");
        }
        return commandLine.getOptionValue(outputDirectoryOption);
    }

    private String getSorterName() {
        if (!commandLine.hasOption(sorterNameOption)) {
            throw new RuntimeException("-s opt is required. See -h or -help for all necessary information.");
        }
        return commandLine.getOptionValue(sorterNameOption);
    }

    private boolean containsHelpOption() {
        return commandLine.hasOption(helpOption);
    }
}
