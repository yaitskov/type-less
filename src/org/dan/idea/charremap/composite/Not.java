package org.dan.idea.charremap.composite;

import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class Not implements Matcher {
    private final Matcher forward;

    public static Not not(Matcher m) {
        return new Not(m);
    }

    public Not(Matcher forward) {
        this.forward = forward;
    }

    @Override
    public boolean test(MatcherState s) {
        return !forward.test(s);
    }
}
