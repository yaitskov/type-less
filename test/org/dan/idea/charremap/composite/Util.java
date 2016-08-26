package org.dan.idea.charremap.composite;

import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.tree.IElementType;

public class Util {
    public static ASTNode seqUp(IElementType... types) {
        ASTNode result = null;
        for (IElementType type : types) {
            ASTNode parent = new CompositeElement(type);
            if (result != null) {
                parent.addChild(result);
            }
            result = parent;

        }
        return result;
    }
}
