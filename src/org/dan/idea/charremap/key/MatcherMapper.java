package org.dan.idea.charremap.key;

import static com.intellij.psi.impl.java.stubs.JavaStubElementTypes.JAVA_FILE;
import static com.intellij.psi.impl.source.tree.JavaElementType.CLASS;
import static com.intellij.psi.impl.source.tree.JavaElementType.FIELD;
import static com.intellij.psi.impl.source.tree.JavaElementType.METHOD;
import static com.intellij.psi.impl.source.tree.JavaElementType.MODIFIER_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER_LIST;
import static java.util.Optional.of;
import static org.dan.idea.charremap.composite.Any.any;
import static org.dan.idea.charremap.composite.Maybe.maybe;
import static org.dan.idea.charremap.composite.Not.not;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.One.one;
import static org.dan.idea.charremap.composite.Or.or;
import static org.dan.idea.charremap.composite.Plus.plus;
import static org.dan.idea.charremap.composite.PrevChar.prevChar;
import static org.dan.idea.charremap.composite.Seq.seq;
import static org.slf4j.LoggerFactory.getLogger;

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

public abstract class MatcherMapper implements Mapper {
    private static final Logger logger = getLogger(MatcherMapper.class);

    public static Matcher AT_MATCHER = seq(WS,
            or(
                    seq(one(FIELD), plus(CLASS)),
                    seq(maybe(PARAMETER), one(PARAMETER_LIST),
                            one(METHOD), plus(CLASS)),
                    seq(maybe(MODIFIER_LIST), any(CLASS))),
            one(JAVA_FILE),
            not(prevChar(Character::isJavaIdentifierPart)));

    public static List<IElementType> types(ASTNode node) {
        List<IElementType> result = new ArrayList<>();
        while (node != null) {
            result.add(node.getElementType());
            node = node.getTreeParent();
        }
        return result;
    }

    @Override
    public Optional<Character> apply(CharEvent ce) {
        PsiElement root = ce.pf.getNode().getPsi();
        final int offset = ce.editor.getCaretModel().getOffset();

        PsiElement elementAt = root.findElementAt(offset);
        if (elementAt == null) {
            return of(ce.origin);
        }
        logger.info("Path to current element [{}]\nnext [{}]\nprev [{}}",
                MatcherMapper.types(elementAt.getNode()),
                MatcherMapper.types(elementAt.getNode().getTreeNext()),
                MatcherMapper.types(elementAt.getNode().getTreePrev()));
        String docText = ce.editor.getDocument().getText();
        MatcherState state = new MatcherState(docText, offset, elementAt.getNode());
        return map(state);
    }

    protected abstract Optional<Character> map(MatcherState state);
}
