package org.dan.idea.charremap.composite;

import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;

public class Seq implements Matcher {
    private final Matcher[] matchers;

    public Seq(Matcher... matchers) {
        this.matchers = matchers;
    }

    public static Seq seq(Matcher... matchers) {
        return new Seq(matchers);
    }

    @Override
    public boolean test(MatcherState state) {
        for (Matcher m : matchers) {
            if (!m.test(state)) {
                return false;
            }
        }
        return true;
    }
}
