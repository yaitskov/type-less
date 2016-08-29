package org.dan.idea.charremap.composite;

import static org.dan.idea.charremap.matcher.MatcherState.coalesce;

import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class LastChild implements Matcher {
    private final Matcher forward;

    private LastChild(Matcher forward) {
        this.forward = forward;
    }

    public static Matcher lastChild(Matcher forward) {
        return new LastChild(forward);
    }

    @Override
    public boolean test(MatcherState s) {
        s.setNode(coalesce(s.getNode().getLastChildNode()));
        return forward.test(s);
    }
}
