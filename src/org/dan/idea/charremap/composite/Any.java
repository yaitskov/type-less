package org.dan.idea.charremap.composite;

import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;

public class Any implements Matcher {
    private final IElementType expectedElement;

    public Any(IElementType expectedElement) {
        this.expectedElement = expectedElement;
    }

    public static Any any(IElementType expectedElement) {
        return new Any(expectedElement);
    }

    @Override
    public boolean test(MatcherState matcherState) {
        while (expectedElement == matcherState.type()) {
            matcherState.next();
        }
        return true;
    }
}