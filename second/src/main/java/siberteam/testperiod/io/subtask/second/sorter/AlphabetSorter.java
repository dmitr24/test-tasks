package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@SorterInfo(name = "Alphabet sorter", description = "Sorting by alphabet order from A to Z")
public class AlphabetSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .sorted()
                .collect(Collectors.joining("\n"));
    }
}
