package siberteam.testperiod.io.subtask.first.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import siberteam.testperiod.io.subtask.common.util.TextUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Analyzer {
    public static Map<String, Float> analyze(String text) {
        List<String> distinctLetters = TextUtils.getDistinctLetters(text);
        Map<String, Float> result = new HashMap<>();
        if (distinctLetters.size() == 1 && distinctLetters.get(0).equals("")) {
            return result;
        }
        int textLength = text.length();
        for (String distinctLetter : distinctLetters) {
            int count = textLength - text.replace(distinctLetter, "").length();
            float percentage =  ((float) count) / textLength * 100;
            result.put(distinctLetter, percentage);
        }
        return result;
    }
}
