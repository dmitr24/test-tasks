package siberteam.testperiod.io.subtask.first.io.writer;

import siberteam.testperiod.io.subtask.first.exception.WriterException;

public interface Writer<T> {
    void write(T text) throws WriterException;
}
