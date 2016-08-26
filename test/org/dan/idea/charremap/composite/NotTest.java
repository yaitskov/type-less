package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.MatcherState.of;
import static org.dan.idea.charremap.composite.Util.seqUp;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NotTest {
    @Test
    public void not() {
        assertFalse(Not.not(x -> true).test(of(seqUp(WHITE_SPACE))));
        assertTrue(Not.not(x -> false).test(of(seqUp(WHITE_SPACE))));
    }
}
