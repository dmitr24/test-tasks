package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.common.data.Text;
import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@SorterInfo(name = "Unique letters sorter", description = "Sorting by сщгте ща гтшйгу дуееукы шт цщкв")
public class UniqueLettersCountsSorter implements Sorter {
    @Override
    public String sort(Text text) {
        return text
                .getNotEmptyWords()
                .sorted(Comparator.comparingInt(value -> text.getDistinctLetters().size()))
                .collect(Collectors.joining("\n"));
    }
}
