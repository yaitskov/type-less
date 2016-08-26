package org.dan.idea.charremap.composite;

import com.intellij.psi.tree.IElementType;

public class Util {
    public static MockNode seqUp(IElementType... types) {
        MockNode result = null;
        for (IElementType type : types) {
            MockNode parent = new MockNode(type);
            if (result != null) {
                parent.addChild(result);
            }
            result = parent;
        }
        return result;
    }
}
