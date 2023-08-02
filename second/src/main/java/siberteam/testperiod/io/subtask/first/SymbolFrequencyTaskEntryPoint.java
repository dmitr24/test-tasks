package siberteam.testperiod.io.subtask.first;

import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import siberteam.testperiod.io.subtask.first.processor.SymbolFrequencyTaskProcessor;

public class SymbolFrequencyTaskEntryPoint {
    public static void main(String[] args) {
        validateInput(args);
        analyzeSymbolFrequency(args[0]);
    }

    private static void validateInput(String[] args) {
        if (args.length == 0) {
            System.err.println("File location have to be specified");
            System.exit(1);
        }
        String fileLocation = args[0];
        FileValidator fileValidator = new FileValidator();
        if (!fileValidator.validate(fileLocation)) {
            System.err.println("File " + fileLocation + " not found");
            System.exit(1);
        }
    }

    private static void analyzeSymbolFrequency(String fileLocation) {
        SymbolFrequencyTaskProcessor taskProcessor = new SymbolFrequencyTaskProcessor();
        taskProcessor.process(fileLocation);
    }
}
