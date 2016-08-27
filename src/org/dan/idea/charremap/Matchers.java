package org.dan.idea.charremap;

import static com.intellij.psi.impl.java.stubs.JavaStubElementTypes.JAVA_FILE;
import static com.intellij.psi.impl.source.tree.JavaElementType.CLASS;
import static com.intellij.psi.impl.source.tree.JavaElementType.FIELD;
import static com.intellij.psi.impl.source.tree.JavaElementType.METHOD;
import static com.intellij.psi.impl.source.tree.JavaElementType.MODIFIER_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER_LIST;
import static org.dan.idea.charremap.composite.Any.any;
import static org.dan.idea.charremap.composite.Maybe.maybe;
import static org.dan.idea.charremap.composite.Not.not;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.One.one;
import static org.dan.idea.charremap.composite.Or.or;
import static org.dan.idea.charremap.composite.Plus.plus;
import static org.dan.idea.charremap.composite.PrevChar.prevChar;
import static org.dan.idea.charremap.composite.Seq.seq;

public class Matchers {
    public static Matcher AT_MATCHER = seq(WS,
            or(
                    seq(one(FIELD), plus(CLASS)),
                    seq(maybe(PARAMETER), one(PARAMETER_LIST),
                            one(METHOD), plus(CLASS)),
                    seq(maybe(MODIFIER_LIST), any(CLASS))),
            one(JAVA_FILE),
            not(prevChar(Character::isJavaIdentifierPart)));
}
