package sbierteam.tests.task;

import sbierteam.tests.exception.NullCensoredTextProvidedException;
import java.util.Objects;

public class Censor {
    private final String censurable;

    public Censor(String censurable) {
        if (Objects.isNull(censurable)) {
            throw new NullCensoredTextProvidedException("censurable word can't be null");
        }
        this.censurable = censurable;
    }

    public String apply(String text) {
        return text == null ? null : text.replace(censurable, "censored");
    }
}