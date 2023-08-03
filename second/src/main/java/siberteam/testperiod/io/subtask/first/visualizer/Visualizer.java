package siberteam.testperiod.io.subtask.first.visualizer;

import java.util.Map;

public interface Visualizer<R> {
    R visualize(Map<Character, Integer> charCount);
}
