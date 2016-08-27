package org.dan.idea.charremap.composite;

import static com.intellij.psi.TokenType.CODE_FRAGMENT;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static org.junit.Assert.assertEquals;

import com.intellij.psi.tree.IElementType;
import org.junit.Test;

public class Util {
    public static MockNode seqUp(IElementType... types) {
        MockNode parent = null;
        MockNode result = null;
        for (IElementType type : types) {
            MockNode child = new MockNode(type);
            if (parent == null) {
                result = child;
            } else {
                child.addChild(parent);
            }
            parent = child;
        }
        return result;
    }

    @Test
    public void testSeqUp() {
        MockNode node = seqUp(WHITE_SPACE, CODE_FRAGMENT);
        assertEquals(WHITE_SPACE, node.getElementType());
        assertEquals(CODE_FRAGMENT, node.getTreeParent().getElementType());
    }
}
