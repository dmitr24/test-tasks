package siberteam.testperiod.io.subtask.first;

import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import siberteam.testperiod.io.subtask.first.buffer.CharCountBuffer;
import siberteam.testperiod.io.subtask.first.data.mapper.CharPercentageAndHistogramRowsMapper;
import siberteam.testperiod.io.subtask.first.io.FileReader;
import siberteam.testperiod.io.subtask.first.io.FileWriter;
import siberteam.testperiod.io.subtask.first.processor.CharStatisticsProcessor;
import siberteam.testperiod.io.subtask.first.visualizer.SortedCharPercentageAndHistogramVisualizer;

public class CharStatisticsTask {
    private static final String OUTPUT_DIRECTORY = "/home/dmitryk/projects/main/second/src/main/resources/first";

    public static void main(String[] args) {
        validateInput(args);
        processFile(args[0]);
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

    private static void processFile(String fileLocation) {
        FileReader reader = new FileReader(fileLocation);
        FileWriter writer = new FileWriter(OUTPUT_DIRECTORY);
        CharPercentageAndHistogramRowsMapper dataMapper = new CharPercentageAndHistogramRowsMapper();
        SortedCharPercentageAndHistogramVisualizer visualizer =
                new SortedCharPercentageAndHistogramVisualizer('#');
        CharCountBuffer symbolCountBuffer = new CharCountBuffer();
        CharStatisticsProcessor taskProcessor = new CharStatisticsProcessor(symbolCountBuffer, dataMapper, visualizer, reader, writer);
        taskProcessor.process();
    }
}
