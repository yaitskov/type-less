package org.dan.idea.charremap.key;

import static com.intellij.psi.impl.java.stubs.JavaStubElementTypes.JAVA_FILE;
import static com.intellij.psi.impl.source.tree.JavaElementType.CLASS;
import static com.intellij.psi.impl.source.tree.JavaElementType.MODIFIER_LIST;
import static java.util.Optional.of;
import static org.dan.idea.charremap.composite.Any.any;
import static org.dan.idea.charremap.composite.Maybe.maybe;
import static org.dan.idea.charremap.composite.Not.not;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.One.one;
import static org.dan.idea.charremap.composite.PrevChar.prevChar;
import static org.dan.idea.charremap.composite.Seq.seq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.dan.idea.charremap.CharEvent;
import org.dan.idea.charremap.Mapper;
import org.dan.idea.charremap.Matcher;
import org.dan.idea.charremap.MatcherState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberTwoMapper implements Mapper {
    private static final Logger logger = LoggerFactory.getLogger(NumberTwoMapper.class);

    @Override
    public Optional<Character> apply(CharEvent ce) {
        PsiElement root = ce.pf.getNode().getPsi();
        final int offset = ce.editor.getCaretModel().getOffset();

        PsiElement elementAt = root.findElementAt(offset);
        if (elementAt == null) {
            return of(ce.origin);
        }
        logger.info("Path to current element [{}]\nnext [{}]\nprev [{}}",
                types(elementAt.getNode()),
                types(elementAt.getNode().getTreeNext()),
                types(elementAt.getNode().getTreePrev()));
        String docText = ce.editor.getDocument().getText();
        MatcherState state = new MatcherState(docText, offset, elementAt.getNode());
        Matcher m = seq(WS, maybe(MODIFIER_LIST), any(CLASS), one(JAVA_FILE),
                not(prevChar(Character::isJavaIdentifierPart)));
        if (m.test(state)) {
            return of('@');
        }
        return of(ce.origin);
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
