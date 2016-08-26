package org.dan.idea.charremap.composite;

import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;

public class Maybe implements Matcher {
    private final IElementType expectedElement;

    public Maybe(IElementType expectedElement) {
        this.expectedElement = expectedElement;
    }

    public static Maybe maybe(IElementType expectedElement) {
        return new Maybe(expectedElement);
    }

    @Override
    public boolean test(MatcherState matcherState) {
        if (expectedElement == matcherState.type()) {
            matcherState.next();
        }
        return true;
    }
}
