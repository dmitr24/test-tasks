package siberteam.testperiod.io.subtask.first.data.mapper;

import java.util.Map;

public interface DataMapper<R> {
    R map(Map<Character, Integer> charsCount);
}
