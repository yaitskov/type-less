package org.dan.idea.charremap.matcher;

import static java.util.Optional.ofNullable;
import static org.dan.idea.charremap.composite.NullAstNode.NULL_AST_NODE;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import groovyjarjarantlr.collections.AST;

public interface MatcherState {
    static ASTNode coalesce(ASTNode node) {
        return ofNullable(node).orElse(NULL_AST_NODE);
    }

    int offset();
    String docText();
    void next();
    IElementType type();
    ASTNode getNode();
    void setNode(ASTNode node);
    ASTNode getOriginNode();
}
