package org.dan.idea.charremap.composite;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NullAstNode implements ASTNode {
    public static final NullAstNode NULL_AST_NODE = new NullAstNode();
    public static final IElementType NULL_ELEMENT_TYPE = null;

    private NullAstNode() {}

    @NotNull
    @Override
    public IElementType getElementType() {
        return null;
    }

    @NotNull
    @Override
    public String getText() {
        return null;
    }

    @NotNull
    @Override
    public CharSequence getChars() {
        return null;
    }

    @Override
    public boolean textContains(char c) {
        return false;
    }

    @Override
    public int getStartOffset() {
        return 0;
    }

    @Override
    public int getTextLength() {
        return 0;
    }

    @Override
    public TextRange getTextRange() {
        return null;
    }

    @Override
    public ASTNode getTreeParent() {
        return NULL_AST_NODE;
    }

    @Override
    public ASTNode getFirstChildNode() {
        return null;
    }

    @Override
    public ASTNode getLastChildNode() {
        return null;
    }

    @Override
    public ASTNode getTreeNext() {
        return null;
    }

    @Override
    public ASTNode getTreePrev() {
        return null;
    }

    @NotNull
    @Override
    public ASTNode[] getChildren(@Nullable TokenSet tokenSet) {
        return new ASTNode[0];
    }

    @Override
    public void addChild(@NotNull ASTNode astNode) {

    }

    @Override
    public void addChild(@NotNull ASTNode astNode, @Nullable ASTNode astNode1) {

    }

    @Override
    public void addLeaf(@NotNull IElementType iElementType, CharSequence charSequence, @Nullable ASTNode astNode) {

    }

    @Override
    public void removeChild(@NotNull ASTNode astNode) {

    }

    @Override
    public void removeRange(@NotNull ASTNode astNode, ASTNode astNode1) {

    }

    @Override
    public void replaceChild(@NotNull ASTNode astNode, @NotNull ASTNode astNode1) {

    }

    @Override
    public void replaceAllChildrenToChildrenOf(ASTNode astNode) {

    }

    @Override
    public void addChildren(ASTNode astNode, ASTNode astNode1, ASTNode astNode2) {

    }

    @NotNull
    @Override
    public Object clone() {
        return null;
    }

    @Override
    public ASTNode copyElement() {
        return null;
    }

    @Nullable
    @Override
    public ASTNode findLeafElementAt(int i) {
        return null;
    }

    @Nullable
    @Override
    public <T> T getCopyableUserData(@NotNull Key<T> key) {
        return null;
    }

    @Override
    public <T> void putCopyableUserData(@NotNull Key<T> key, T t) {

    }

    @Nullable
    @Override
    public ASTNode findChildByType(IElementType iElementType) {
        return null;
    }

    @Nullable
    @Override
    public ASTNode findChildByType(IElementType iElementType, @Nullable ASTNode astNode) {
        return null;
    }

    @Nullable
    @Override
    public ASTNode findChildByType(@NotNull TokenSet tokenSet) {
        return null;
    }

    @Nullable
    @Override
    public ASTNode findChildByType(@NotNull TokenSet tokenSet, @Nullable ASTNode astNode) {
        return null;
    }

    @Override
    public PsiElement getPsi() {
        return null;
    }

    @Override
    public <T extends PsiElement> T getPsi(@NotNull Class<T> aClass) {
        return null;
    }

    @Nullable
    @Override
    public <T> T getUserData(@NotNull Key<T> key) {
        return null;
    }

    @Override
    public <T> void putUserData(@NotNull Key<T> key, @Nullable T t) {

    }
}
