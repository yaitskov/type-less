package org.dan.idea.charremap.composite;

import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class Plus implements Matcher {
    private final IElementType expectedElement;

    public Plus(IElementType expectedElement) {
        this.expectedElement = expectedElement;
    }

    public static Plus plus(IElementType expectedElement) {
        return new Plus(expectedElement);
    }

    @Override
    public boolean test(MatcherState matcherState) {
        if (expectedElement == matcherState.type()) {
            matcherState.next();
            while (expectedElement == matcherState.type()) {
                matcherState.next();
            }
            return true;
        }
        return false;
    }
}
