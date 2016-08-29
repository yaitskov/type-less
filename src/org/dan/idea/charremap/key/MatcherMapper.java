package org.dan.idea.charremap.key;

import static java.util.Optional.of;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.IdeFacade;
import org.dan.idea.charremap.Mapper;
import org.dan.idea.charremap.matcher.MatcherState;
import org.dan.idea.charremap.matcher.ParentMatcherState;

public abstract class MatcherMapper implements Mapper {
    public static List<IElementType> typesOfParents(ASTNode node) {
        List<IElementType> result = new ArrayList<>();
        while (node != null) {
            result.add(node.getElementType());
            node = node.getTreeParent();
        }
        return result;
    }

    public static List<IElementType> typesOfNextSiblings(ASTNode node) {
        List<IElementType> result = new ArrayList<>();
        while (node != null) {
            result.add(node.getElementType());
            node = node.getTreeNext();
        }
        return result;
    }

    public static List<IElementType> typesOfPrevSiblings(ASTNode node) {
        List<IElementType> result = new ArrayList<>();
        while (node != null) {
            result.add(node.getElementType());
            node = node.getTreePrev();
        }
        return result;
    }

    @Override
    public Optional<Character> apply(IdeFacade ide) {
        return of(map(new ParentMatcherState(ide.text(), ide.offset(), ide.eventNode()))
                .orElse(ide.eventChar()));
    }

    protected abstract Optional<Character> map(MatcherState state);
}
