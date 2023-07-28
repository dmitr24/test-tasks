package siberteam.testperiod.io.subtask.first.processor;

import siberteam.testperiod.io.subtask.common.io.FileReader;
import siberteam.testperiod.io.subtask.common.io.FileWriter;
import siberteam.testperiod.io.subtask.first.domain.Analyzer;
import siberteam.testperiod.io.subtask.first.parser.HistogramParser;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class TaskProcessor {
    public void processTask(String dir, String fileName) {
        try {
            Path path = Paths.get(dir + fileName);
            FileReader reader = new FileReader(path);
            Map<Character, Float> histogramData = analyze(reader);
            HistogramParser histogramParser = new HistogramParser("-");
            String output = histogramParser.parseToText(histogramData);
            FileWriter writer = new FileWriter(dir);
            writer.write(output);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }
    }

    private Map<Character, Float> analyze(FileReader reader) throws IOException {
        try (Stream<String> lines = reader.getNotClosedLinesStream()) {
            Analyzer analyzer = new Analyzer();
            return analyzer.analyze(lines);
        }
    }
}
