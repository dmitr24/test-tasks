package siberteam.testperiod.io.subtask.first.processor;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.first.buffer.CharCountBuffer;
import siberteam.testperiod.io.subtask.first.data.mapper.DataMapper;
import siberteam.testperiod.io.subtask.first.exception.CharProcessorException;
import siberteam.testperiod.io.subtask.first.exception.ReaderException;
import siberteam.testperiod.io.subtask.first.exception.WriterException;
import siberteam.testperiod.io.subtask.first.io.reader.Reader;
import siberteam.testperiod.io.subtask.first.io.writer.Writer;
import siberteam.testperiod.io.subtask.first.visualizer.Visualizer;

@RequiredArgsConstructor
public class ConsecutiveCharStatisticsProcessor<R, T> {
    private final CharCountBuffer charCountBuffer;
    private final DataMapper<T> dataMapper;
    private final Visualizer<R, T> visualizer;
    private final Reader reader;
    private final Writer<R> writer;

    public void process() {
        try {
            reader.readToBuffer(charCountBuffer::append);
            T data = dataMapper.map(charCountBuffer.getActualState());
            R result = visualizer.visualize(data);
            writer.write(result);
        } catch (WriterException exception) {
            System.err.println(exception.getMessage());
            throw new CharProcessorException("Error occurred when writing: " + exception.getMessage());
        } catch (ReaderException exception) {
            throw new CharProcessorException("Error occurred when reading: " + exception.getMessage());
        }
    }
}
