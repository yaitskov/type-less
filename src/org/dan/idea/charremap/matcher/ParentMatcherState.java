package org.dan.idea.charremap.matcher;

import static org.dan.idea.charremap.matcher.MatcherState.coalesce;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

public class ParentMatcherState implements MatcherState {
    private final String docText;
    private final int offset;
    private final ASTNode origin;
    private ASTNode node;

    public ParentMatcherState(String docText, int offset, ASTNode node) {
        this.docText = docText;
        this.offset = offset;
        this.node = origin =node;
    }

    public int offset() {
        return offset;
    }

    public String docText() {
        return docText;
    }

    public void next() {
        setNode(coalesce(getNode().getTreeParent()));
    }

    public IElementType type() {
        return node.getElementType();
    }

    public ASTNode getNode() {
        return node;
    }

    public void setNode(ASTNode node) {
        this.node = node;
    }

    public ASTNode getOriginNode() {
        return origin;
    }

    public static ParentMatcherState of(ASTNode node) {
        return new ParentMatcherState(" ", 0, node);
    }
}
