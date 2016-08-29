package org.dan.idea.charremap.matcher;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;

public class ForwardMatcherState implements MatcherState {
    private final MatcherState state;

    public ForwardMatcherState(MatcherState state) {
        this.state = state;
    }

    @Override
    public int offset() {
        return state.offset();
    }

    @Override
    public String docText() {
        return state.docText();
    }

    @Override
    public void next() {
        state.next();
    }

    @Override
    public IElementType type() {
        return state.type();
    }

    @Override
    public ASTNode getNode() {
        return state.getNode();
    }

    @Override
    public void setNode(ASTNode node) {
        state.setNode(node);
    }

    @Override
    public ASTNode getOriginNode() {
        return state.getOriginNode();
    }
}
