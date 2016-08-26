package org.dan.idea.charremap;

import static java.util.Optional.ofNullable;
import static org.dan.idea.charremap.composite.NullAstNode.NULL_AST_NODE;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;


public class MatcherState {
    public final String docText;
    public final int offset;
    private ASTNode node;

    public MatcherState(String docText, int offset, ASTNode node) {
        this.docText = docText;
        this.offset = offset;
        this.node = node;
    }

    public void next() {
        node = ofNullable(node.getTreeParent()).orElse(NULL_AST_NODE);
    }

    public IElementType type() {
        return node.getElementType();
    }

    public static MatcherState of(ASTNode node) {
        return new MatcherState("", 0, node);
    }

    public ASTNode getNode() {
        return node;
    }
}
