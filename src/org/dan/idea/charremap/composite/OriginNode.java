package org.dan.idea.charremap.composite;

import static org.dan.idea.charremap.composite.KeepNode.keepNode;

import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class OriginNode implements Matcher {
    private final Matcher forward;

    public OriginNode(Matcher forward) {
        this.forward = forward;
    }

    public static Matcher originNode(Matcher forward) {
        return keepNode(new OriginNode(forward));
    }

    @Override
    public boolean test(MatcherState s) {
        s.setNode(s.getOriginNode());
        return forward.test(s);
    }
}
