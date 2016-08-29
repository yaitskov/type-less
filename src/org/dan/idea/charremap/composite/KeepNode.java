package org.dan.idea.charremap.composite;

import com.intellij.lang.ASTNode;
import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class KeepNode implements Matcher {
    private final Matcher forward;

    public KeepNode(Matcher forward) {
        this.forward = forward;
    }

    public static KeepNode keepNode(Matcher forward) {
        return new KeepNode(forward);
    }

    @Override
    public boolean test(MatcherState state) {
        final ASTNode node = state.getNode();
        try {
            return forward.test(state);
        } finally {
            state.setNode(node);
        }
    }
}
