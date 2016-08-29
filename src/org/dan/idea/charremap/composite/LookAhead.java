package org.dan.idea.charremap.composite;

import static org.dan.idea.charremap.composite.KeepNode.keepNode;

import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class LookAhead implements Matcher {
    private final Matcher forward;

    private LookAhead(Matcher forward) {
        this.forward = forward;
    }

    public static Matcher lookAhead(Matcher forward) {
        return new LookAhead(keepNode(forward));
    }

    @Override
    public boolean test(MatcherState state) {
        while (state.type() != NullAstNode.NULL_ELEMENT_TYPE) {
            if (forward.test(state)) {
                return true;
            }
            state.next();
        }
        return false;
    }
}
