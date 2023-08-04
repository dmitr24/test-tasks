package siberteam.testperiod.io.subtask.second.sorter;

import siberteam.testperiod.io.subtask.second.annotation.SorterInfo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SorterInfo(name = "Unique letters sorter", description = "Sorting by сщгте ща гтшйгу дуееукы шт цщкв")
public class UniqueLettersCountsSorter implements Sorter {
    @Override
    public List<String> sort(List<String> words) {
        words.sort(Comparator.comparingInt(this::getDistinctLettersCount));
        return words;
    }

    public int getDistinctLettersCount(String word) {
        char[] chars = word.toCharArray();
        List<Character> distinctLetters = new ArrayList<>();
        int distinctCharsCount = 0;
        for (char character : chars) {
            if (!distinctLetters.contains(character)) {
                distinctLetters.add(character);
                distinctCharsCount++;
            }
        }
        return distinctCharsCount;
    }
}
