package org.dan.idea.charremap;

import static java.util.Optional.of;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.java.stubs.JavaStubElementTypes;
import com.intellij.psi.impl.source.tree.JavaElementType;
import com.intellij.psi.tree.IElementType;

public class NumberTwoMapper implements Mapper {
    @Override
    public Optional<Character> apply(CharEvent ce) {
        PsiElement root = ce.pf.getNode().getPsi();
        final int offset = ce.editor.getCaretModel().getOffset();

        PsiElement elementAt = root.findElementAt(offset);
        if (elementAt == null) {
            return of(ce.origin);
        }

        if (match(elementAt, TokenType.WHITE_SPACE, JavaStubElementTypes.JAVA_FILE)
                || (match(elementAt, TokenType.WHITE_SPACE,
                JavaElementType.MODIFIER_LIST,
                JavaElementType.CLASS,
                JavaStubElementTypes.JAVA_FILE)
                ) && previousChar(ce, c -> !Character.isJavaIdentifierPart(c))) {
            return of('@');
        }
        return of(ce.origin);

    }

    public static boolean previousChar(CharEvent ce, Predicate<Character> p) {
        int offset = ce.editor.getCaretModel().getOffset();
        return offset > 0 && p.test(ce.editor.getDocument().getText().charAt(offset - 1));
    }

    public static boolean match(PsiElement element, IElementType... types) {
        ASTNode node = element.getNode();
        for (IElementType type : types) {
            if (node == null) {
                return false;
            }
            if (node.getElementType() != type) {
                return false;
            }
            node = node.getTreeParent();
        }
        return true;
    }

    public static List<IElementType> types(ASTNode node) {
        List<IElementType> result = new ArrayList<>();
        while (node != null) {
            result.add(node.getElementType());
            node = node.getTreeParent();
        }
        return result;
    }
}
