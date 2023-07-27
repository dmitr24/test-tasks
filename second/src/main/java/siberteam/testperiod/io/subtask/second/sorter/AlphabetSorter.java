package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class AlphabetSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .sorted()
                .collect(Collectors.joining("\n"));
    }
}
