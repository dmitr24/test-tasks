package siberteam.testperiod.io.subtask.first.processor;

import siberteam.testperiod.io.subtask.common.io.FileWriter;
import siberteam.testperiod.io.subtask.first.domain.SymbolFrequencyHistogramBuilder;
import siberteam.testperiod.io.subtask.first.parser.HistogramParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class SymbolFrequencyTaskProcessor {
    private static final String OUTPUT_DIRECTORY = "/home/dmitryk/projects/main/second/src/main/resources/first";
    private final SymbolFrequencyHistogramBuilder histogramBuilder;
    private final HistogramParser histogramParser;

    public SymbolFrequencyTaskProcessor() {
        this.histogramBuilder = new SymbolFrequencyHistogramBuilder();
        this.histogramParser = new HistogramParser("-");
    }

    public void process(String targetFileLocation) {
        try {
            Map<Character, Float> histogramData = getEachSymbolFrequencyFromText(targetFileLocation);
            String output = histogramParser.parseHistogram(histogramData);
            FileWriter writer = new FileWriter(OUTPUT_DIRECTORY);
            writer.write(output);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }
    }

    private Map<Character, Float> getEachSymbolFrequencyFromText(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String newLine = reader.readLine();
            while (newLine != null) {
                histogramBuilder.append(newLine);
                newLine = reader.readLine();
            }
            return histogramBuilder.build();
        }
    }
}
