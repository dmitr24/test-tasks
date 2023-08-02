package siberteam.testperiod.io.subtask.first.processor;

import siberteam.testperiod.io.subtask.first.buffer.SymbolCountBuffer;
import siberteam.testperiod.io.subtask.first.io.reader.FileReader;
import siberteam.testperiod.io.subtask.first.io.reader.Reader;
import siberteam.testperiod.io.subtask.first.io.writer.Writer;
import siberteam.testperiod.io.subtask.first.visualizer.HistogramVisualizer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SymbolFrequencyTaskProcessor {
    private static final String OUTPUT_DIRECTORY = "/home/dmitryk/projects/main/second/src/main/resources/first";
    private final SymbolCountBuffer histogramBuilder;
    private final HistogramVisualizer histogramVisualizer;
    private final Reader reader;
    private final Writer writer;


    public SymbolFrequencyTaskProcessor() {
        this.histogramBuilder = new SymbolCountBuffer();
        this.histogramVisualizer = new HistogramVisualizer("-");
    }

    public void process(String targetFileLocation) {
        try {
            Map<Character, Integer> symbolCount = getEachSymbolFrequencyFromText(targetFileLocation);
            String result = histogramVisualizer.visualize(symbolCount);
            writeResult(result);
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    private Map<Character, Integer> getEachSymbolFrequencyFromText(String path) {
        FileReader reader = new FileReader(path);
        reader.readToBuffer(histogramBuilder::append);
        return histogramBuilder.getActualState();
    }

    private void writeResult(String result) throws IOException {
        FileWriter writer = new FileWriter(OUTPUT_DIRECTORY);
        writer.write(result);
    }
}
