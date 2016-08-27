package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.composite.PrevChar.prevChar;
import static org.dan.idea.charremap.composite.Util.seqUp;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intellij.psi.TokenType;
import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;
import org.junit.Test;

public class PrevCharTest {
    @Test
    public void zeroOffset() {
        Matcher m = prevChar(c -> { throw new IllegalStateException("not expected"); });
        assertFalse(m.test(new MatcherState("a", 0, seqUp(WHITE_SPACE))));
    }

    @Test
    public void offset1PredicateTrue() {
        Matcher m = prevChar(c -> c == 'a');
        assertTrue(m.test(new MatcherState("ab", 1, seqUp(WHITE_SPACE))));
    }

    @Test
    public void offset1PredicateFalse() {
        Matcher m = prevChar(c -> c == 'b');
        assertFalse(m.test(new MatcherState("ab", 1, seqUp(WHITE_SPACE))));
    }
}
