package org.dan.idea.charremap.composite;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;

public class One implements Matcher {
    public static final One WS = one(TokenType.WHITE_SPACE);

    private final IElementType expectedElement;

    public One(IElementType expectedElement) {
        this.expectedElement = expectedElement;
    }

    public static One one(IElementType expectedElement) {
        return new One(expectedElement);
    }

    @Override
    public boolean test(MatcherState matcherState) {
        if (expectedElement == matcherState.type()) {
            matcherState.next();
            return true;
        }
        return false;
    }
}
