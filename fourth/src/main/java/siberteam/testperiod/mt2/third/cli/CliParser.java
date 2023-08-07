package siberteam.testperiod.mt2.third.cli;

import org.apache.commons.cli.*;
import siberteam.testperiod.mt2.third.data.Locations;

public class CliParser {
    private final Option fileNameOption = new Option("i", true, "FileName for parsing");
    private final Option outputDirectoryOption = new Option("o", true, "Directory which contains file");
    private CommandLine commandLine;

    private Options getOptionsList() {
        Options options = new Options();
        options.addOption(fileNameOption);
        options.addOption(outputDirectoryOption);
        return options;
    }

    public Locations parse(String[] args) {
        initCommandLine(args);
        return getLocations();
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

    private Locations getLocations() {
        Locations locations = new Locations();
        locations.setInputFileLocation(getFileName());
        locations.setOutputDirectoryLocation(getDirectoryName());
        return locations;
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
}
