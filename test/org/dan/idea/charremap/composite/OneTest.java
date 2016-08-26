package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.NEW_LINE_INDENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.dan.idea.charremap.MatcherState.of;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.Util.seqUp;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.intellij.lang.ASTNode;
import org.dan.idea.charremap.MatcherState;
import org.junit.Test;

public class OneTest {
    @Test
    public void matchWhiteSpace() {
        MatcherState s = of(seqUp(WHITE_SPACE));
        assertTrue(WS.test(s));
        assertTrue(NullAstNode.NULL_AST_NODE == s.getNode());
    }

    @Test
    public void notMatchWhiteSpace() {
        ASTNode startNode = seqUp(NEW_LINE_INDENT);
        MatcherState s = of(startNode);
        assertTrue(startNode == s.getNode());
        assertFalse(WS.test(s));
        assertTrue(startNode == s.getNode());
    }
}
