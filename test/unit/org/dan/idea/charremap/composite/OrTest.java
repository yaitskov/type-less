package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.CODE_FRAGMENT;
import static com.intellij.psi.TokenType.NEW_LINE_INDENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.matcher.ParentMatcherState.of;
import static org.dan.idea.charremap.composite.NullAstNode.NULL_AST_NODE;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.One.one;
import static org.dan.idea.charremap.composite.Or.or;
import static org.dan.idea.charremap.composite.Util.seqUp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dan.idea.charremap.matcher.MatcherState;
import org.junit.Test;

public class OrTest {
    @Test
    public void firstPass() {
        MatcherState s = of(seqUp(WHITE_SPACE));
        assertTrue(or(WS, one(CODE_FRAGMENT)).test(s));
        assertEquals(NULL_AST_NODE, s.getNode());
    }

    @Test
    public void secondPass() {
        MatcherState s = of(seqUp(CODE_FRAGMENT));
        assertTrue(or(WS, one(CODE_FRAGMENT)).test(s));
        assertEquals(NULL_AST_NODE, s.getNode());
    }

    @Test
    public void noPass() {
        MatcherState s = of(seqUp(NEW_LINE_INDENT));
        assertFalse(or(WS, one(CODE_FRAGMENT)).test(s));
        assertEquals(NEW_LINE_INDENT, s.type());
    }
}
