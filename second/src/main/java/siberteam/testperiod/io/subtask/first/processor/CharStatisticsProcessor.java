package siberteam.testperiod.io.subtask.first.processor;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.first.buffer.CharCountBuffer;
import siberteam.testperiod.io.subtask.first.data.mapper.CharPercentageAndHistogramRowsMapper;
import siberteam.testperiod.io.subtask.first.data.visualization.CharPercentageAndHistogramRow;
import siberteam.testperiod.io.subtask.first.exception.CharProcessorException;
import siberteam.testperiod.io.subtask.first.exception.ReaderException;
import siberteam.testperiod.io.subtask.first.exception.WriterException;
import siberteam.testperiod.io.subtask.first.io.FileReader;
import siberteam.testperiod.io.subtask.first.io.FileWriter;
import siberteam.testperiod.io.subtask.first.visualizer.SortedCharPercentageAndHistogramVisualizer;
import java.util.List;

@RequiredArgsConstructor
public class CharStatisticsProcessor {
    private final CharCountBuffer charCountBuffer;
    private final CharPercentageAndHistogramRowsMapper dataMapper;
    private final SortedCharPercentageAndHistogramVisualizer visualizer;
    private final FileReader reader;
    private final FileWriter writer;

    public void process() {
        try {
            reader.readToBuffer(charCountBuffer::append);
            List<CharPercentageAndHistogramRow> data = dataMapper.map(charCountBuffer.getActualState());
            String result = visualizer.visualize(data);
            writer.write(result);
        } catch (WriterException exception) {
            System.err.println(exception.getMessage());
            throw new CharProcessorException("Error occurred when writing: " + exception.getMessage());
        } catch (ReaderException exception) {
            throw new CharProcessorException("Error occurred when reading: " + exception.getMessage());
        }
    }
}
