package siberteam.testperiod.io.subtask.first.io.reader;

import siberteam.testperiod.io.subtask.first.exception.ReaderException;
import java.util.function.Consumer;

public interface Reader {
    void readToBuffer(Consumer<? super Character> buffer) throws ReaderException;
}
