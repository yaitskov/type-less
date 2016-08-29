package org.dan.idea.charremap.composite;

import static org.dan.idea.charremap.composite.Seq.seq;

import org.dan.idea.charremap.matcher.BackwardMatcherState;
import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class Backward implements Matcher {
    private final Matcher forward;

    private Backward(Matcher forward) {
        this.forward = forward;
    }

    public static Matcher backward(Matcher... forwards) {
        return new Backward(seq(forwards));
    }

    @Override
    public boolean test(MatcherState s) {
        return forward.test(new BackwardMatcherState(s));
    }
}
