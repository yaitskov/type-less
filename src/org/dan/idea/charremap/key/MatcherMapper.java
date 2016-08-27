package org.dan.idea.charremap.key;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.IdeFacade;
import org.dan.idea.charremap.Mapper;
import org.dan.idea.charremap.MatcherState;

public abstract class MatcherMapper implements Mapper {
    public static List<IElementType> types(ASTNode node) {
        List<IElementType> result = new ArrayList<>();
        while (node != null) {
            result.add(node.getElementType());
            node = node.getTreeParent();
        }
        return result;
    }

    @Override
    public Optional<Character> apply(IdeFacade ide) {
        return map(new MatcherState(ide.text(), ide.offset(), ide.eventNode()));
    }

    protected abstract Optional<Character> map(MatcherState state);
}
