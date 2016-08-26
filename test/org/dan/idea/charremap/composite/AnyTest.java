package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.NEW_LINE_INDENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.MatcherState.of;
import static org.dan.idea.charremap.composite.Any.ANY_WS;
import static org.dan.idea.charremap.composite.Util.seqUp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.intellij.lang.ASTNode;
import org.dan.idea.charremap.MatcherState;
import org.junit.Test;

public class AnyTest {
    @Test
    public void matchAll() {
        MatcherState s = of(seqUp(WHITE_SPACE, WHITE_SPACE, WHITE_SPACE));
        assertTrue(ANY_WS.test(s));
        assertTrue(NullAstNode.NULL_AST_NODE == s.getNode());
    }

    @Test
    public void matchAllWhiteSpaces() {
        MatcherState s = of(seqUp(WHITE_SPACE, WHITE_SPACE, NEW_LINE_INDENT));
        assertTrue(ANY_WS.test(s));
        assertEquals(NEW_LINE_INDENT, s.type());
    }

    @Test
    public void matchZeroWhiteSpace() {
        ASTNode startNode = seqUp(NEW_LINE_INDENT);
        MatcherState s = of(startNode);
        assertTrue(startNode == s.getNode());
        assertTrue(ANY_WS.test(s));
        assertTrue(startNode == s.getNode());
    }
}
