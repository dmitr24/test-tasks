package siberteam.testperiod.io.subtask.second.handler;

import lombok.RequiredArgsConstructor;
import siberteam.testperiod.io.subtask.second.data.request.SortRequest;
import siberteam.testperiod.io.subtask.second.exception.ReaderException;
import siberteam.testperiod.io.subtask.second.exception.SorterFactoryException;
import siberteam.testperiod.io.subtask.second.exception.WriterException;
import siberteam.testperiod.io.subtask.second.factory.SorterFactory;
import siberteam.testperiod.io.subtask.second.io.FileReader;
import siberteam.testperiod.io.subtask.second.io.FileWriter;
import java.util.List;

@RequiredArgsConstructor
public class SortRequestHandler {
    private final FileReader reader;
    private final SorterFactory sorterFactory;
    private final FileWriter writer;

    public void handle(SortRequest sortRequest) throws ReaderException,
            SorterFactoryException, WriterException {
        List<String> words = reader.getDistinctWords();
        List<String> sortedWords = sorterFactory
                .getInstance(sortRequest.getSorterName())
                .sort(words);
        writer.write(String.join("\n", sortedWords));
    }
}
