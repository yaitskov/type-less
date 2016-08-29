package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.CODE_FRAGMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.matcher.ParentMatcherState.of;
import static org.dan.idea.charremap.composite.Plus.plus;
import static org.dan.idea.charremap.composite.Util.seqUp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dan.idea.charremap.matcher.MatcherState;
import org.junit.Test;

public class PlusTest {
    @Test
    public void match() {
        MatcherState s = of(seqUp(WHITE_SPACE, WHITE_SPACE, CODE_FRAGMENT));
        assertTrue(plus(WHITE_SPACE).test(s));
        assertEquals(CODE_FRAGMENT, s.type());
    }

    @Test
    public void noMatch() {
        MatcherState s = of(seqUp(WHITE_SPACE, CODE_FRAGMENT));
        assertFalse(plus(CODE_FRAGMENT).test(s));
        assertEquals(WHITE_SPACE, s.type());
    }
}
