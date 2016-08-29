package org.dan.idea.charremap.composite;

import static org.dan.idea.charremap.matcher.MatcherState.coalesce;
import static org.dan.idea.charremap.composite.KeepNode.keepNode;

import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class PrevSibling implements Matcher {
    private final Matcher forward;

    private PrevSibling(Matcher forward) {
        this.forward = forward;
    }

    public static Matcher prevSibling(Matcher forward) {
        return keepNode(new PrevSibling(forward));
    }

    @Override
    public boolean test(MatcherState s) {
        s.setNode(coalesce(s.getNode().getTreePrev()));
        return forward.test(s);
    }
}
