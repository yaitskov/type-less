package org.dan.idea.charremap.composite;

import java.util.function.Predicate;

import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;

public class PrevChar implements Matcher {
    private final Predicate<Character> predicate;

    public PrevChar(Predicate<Character> predicate) {
        this.predicate = predicate;
    }

    public static PrevChar prevChar(Predicate<Character> predicate) {
        return new PrevChar(predicate);
    }

    @Override
    public boolean test(MatcherState s) {
        return s.offset > 0 && predicate.test(s.docText.charAt(s.offset - 1));
    }
}
