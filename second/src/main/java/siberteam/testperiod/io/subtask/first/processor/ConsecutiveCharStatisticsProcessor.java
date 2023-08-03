package siberteam.testperiod.io.subtask.first.processor;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.first.buffer.CharCountBuffer;
import siberteam.testperiod.io.subtask.first.exception.CharProcessorException;
import siberteam.testperiod.io.subtask.first.exception.ReaderException;
import siberteam.testperiod.io.subtask.first.exception.WriterException;
import siberteam.testperiod.io.subtask.first.io.reader.Reader;
import siberteam.testperiod.io.subtask.first.io.writer.Writer;
import siberteam.testperiod.io.subtask.first.visualizer.Visualizer;

@RequiredArgsConstructor
public class ConsecutiveCharStatisticsProcessor<T> {
    private final CharCountBuffer charCountBuffer;
    private final Visualizer<T> visualizer;
    private final Reader reader;
    private final Writer<T> writer;

    public void process() {
        try {
            reader.readToBuffer(charCountBuffer::append);
            T result = visualizer.visualize(charCountBuffer.getActualState());
            writer.write(result);
        } catch (WriterException exception) {
            System.err.println(exception.getMessage());
            throw new CharProcessorException("Error occurred when writing: " + exception.getMessage());
        } catch (ReaderException exception) {
            throw new CharProcessorException("Error occurred when reading: " + exception.getMessage());
        }
    }
}
