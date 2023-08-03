package siberteam.testperiod.io.subtask.first;

import siberteam.testperiod.io.subtask.common.validator.FileValidator;
import siberteam.testperiod.io.subtask.first.buffer.CharCountBuffer;
import siberteam.testperiod.io.subtask.first.data.mapper.CharPercentageAndHistogramRowsMapper;
import siberteam.testperiod.io.subtask.first.data.mapper.DataMapper;
import siberteam.testperiod.io.subtask.first.data.visualization.CharPercentageAndHistogramRow;
import siberteam.testperiod.io.subtask.first.io.reader.FileReader;
import siberteam.testperiod.io.subtask.first.io.reader.Reader;
import siberteam.testperiod.io.subtask.first.io.writer.FileWriter;
import siberteam.testperiod.io.subtask.first.io.writer.Writer;
import siberteam.testperiod.io.subtask.first.processor.ConsecutiveCharStatisticsProcessor;
import siberteam.testperiod.io.subtask.first.visualizer.SortedCharPercentageAndHistogramVisualizer;
import siberteam.testperiod.io.subtask.first.visualizer.Visualizer;

import java.util.List;

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
        Reader reader = new FileReader(fileLocation);
        Writer<String> writer = new FileWriter(OUTPUT_DIRECTORY);
        DataMapper<List<CharPercentageAndHistogramRow>> dataMapper =
                new CharPercentageAndHistogramRowsMapper();
        Visualizer<String, List<CharPercentageAndHistogramRow>> visualizer =
                new SortedCharPercentageAndHistogramVisualizer('#');
        CharCountBuffer symbolCountBuffer = new CharCountBuffer();
        ConsecutiveCharStatisticsProcessor<String, List<CharPercentageAndHistogramRow>> taskProcessor =
                new ConsecutiveCharStatisticsProcessor<>(symbolCountBuffer, dataMapper, visualizer, reader, writer);
        taskProcessor.process();
    }
}
