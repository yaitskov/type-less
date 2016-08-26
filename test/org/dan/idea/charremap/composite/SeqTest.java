package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.CODE_FRAGMENT;
import static com.intellij.psi.TokenType.NEW_LINE_INDENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.MatcherState.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dan.idea.charremap.MatcherState;
import org.junit.Test;

public class SeqTest {
    @Test
    public void allExecuted() {
        MatcherState st = of(Util.seqUp(WHITE_SPACE, WHITE_SPACE, NEW_LINE_INDENT));
        assertTrue(Seq.seq(One.WS, One.WS).test(st));
        assertEquals(NEW_LINE_INDENT, st.type());
    }

    @Test
    public void breakExecuted() {
        MatcherState st = of(Util.seqUp(WHITE_SPACE, NEW_LINE_INDENT, CODE_FRAGMENT));
        assertFalse(Seq.seq(One.WS, One.one(NEW_LINE_INDENT), One.WS).test(st));
        assertEquals(CODE_FRAGMENT, st.type());
    }
}
