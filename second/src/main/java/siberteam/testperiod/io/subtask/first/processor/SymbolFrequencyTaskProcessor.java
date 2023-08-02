package siberteam.testperiod.io.subtask.first.processor;

import siberteam.testperiod.io.subtask.common.io.FileWriter;
import siberteam.testperiod.io.subtask.first.domain.SymbolFrequencyHistogramBuilder;
import siberteam.testperiod.io.subtask.first.io.Reader;
import siberteam.testperiod.io.subtask.first.parser.HistogramParser;
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
            String result = histogramParser.parseHistogram(histogramData);
            writeResult(result);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    private Map<Character, Float> getEachSymbolFrequencyFromText(String path) {
        Reader reader = new Reader(path);
        reader.readToBuilder(histogramBuilder::append);
        return histogramBuilder.build();
    }

    private void writeResult(String result) throws IOException {
        FileWriter writer = new FileWriter(OUTPUT_DIRECTORY);
        writer.write(result);
    }
}
