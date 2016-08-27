package org.dan.idea.charremap.composite;

import static org.dan.idea.charremap.composite.One.one;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;

public class Maybe implements Matcher {
    private final Matcher forward;

    public Maybe(Matcher forward) {
        this.forward = forward;
    }

    public static Maybe maybe(IElementType expectedElement) {
        return new Maybe(one(expectedElement));
    }

    public static Maybe maybe(Matcher forward) {
        return new Maybe(forward);
    }

    @Override
    public boolean test(MatcherState s) {
        ASTNode origin = s.getNode();
        if (!forward.test(s)) {
            s.setNode(origin);
        }
        return true;
    }
}
