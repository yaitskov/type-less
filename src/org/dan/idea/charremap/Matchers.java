package org.dan.idea.charremap;

import static com.intellij.psi.JavaTokenType.AT;
import static com.intellij.psi.JavaTokenType.BOOLEAN_KEYWORD;
import static com.intellij.psi.JavaTokenType.BYTE_KEYWORD;
import static com.intellij.psi.JavaTokenType.DOUBLE_KEYWORD;
import static com.intellij.psi.JavaTokenType.FINAL_KEYWORD;
import static com.intellij.psi.JavaTokenType.FLOAT_KEYWORD;
import static com.intellij.psi.JavaTokenType.IDENTIFIER;
import static com.intellij.psi.JavaTokenType.INT_KEYWORD;
import static com.intellij.psi.JavaTokenType.LONG_KEYWORD;
import static com.intellij.psi.JavaTokenType.PRIVATE_KEYWORD;
import static com.intellij.psi.JavaTokenType.PROTECTED_KEYWORD;
import static com.intellij.psi.JavaTokenType.PUBLIC_KEYWORD;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.intellij.psi.impl.java.stubs.JavaStubElementTypes.JAVA_FILE;
import static com.intellij.psi.impl.source.tree.JavaElementType.ANNOTATION;
import static com.intellij.psi.impl.source.tree.JavaElementType.CLASS;
import static com.intellij.psi.impl.source.tree.JavaElementType.FIELD;
import static com.intellij.psi.impl.source.tree.JavaElementType.JAVA_CODE_REFERENCE;
import static com.intellij.psi.impl.source.tree.JavaElementType.METHOD;
import static com.intellij.psi.impl.source.tree.JavaElementType.MODIFIER_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.TYPE;
import static org.dan.idea.charremap.composite.Any.any;
import static org.dan.idea.charremap.composite.Maybe.maybe;
import static org.dan.idea.charremap.composite.Not.not;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.One.one;
import static org.dan.idea.charremap.composite.Or.or;
import static org.dan.idea.charremap.composite.Plus.plus;
import static org.dan.idea.charremap.composite.PrevChar.prevChar;
import static org.dan.idea.charremap.composite.Seq.seq;

import org.dan.idea.charremap.composite.One;
import org.dan.idea.charremap.composite.Plus;

public class Matchers {
    private static final Plus P_CLASS = plus(CLASS);

    public static final One O_FIELD = one(FIELD);
    public static final One O_MODIFIER_LIST = one(MODIFIER_LIST);
    public static Matcher AT_MATCHER = seq(
            or(
                    seq(or(one(PRIVATE_KEYWORD),
                            one(PUBLIC_KEYWORD),
                            one(PROTECTED_KEYWORD),
                            one(FINAL_KEYWORD)),
                            O_MODIFIER_LIST, O_FIELD, P_CLASS),
                    seq(or(
                            seq(one(IDENTIFIER), one(JAVA_CODE_REFERENCE)),
                            one(INT_KEYWORD),
                            one(DOUBLE_KEYWORD),
                            one(BOOLEAN_KEYWORD),
                            one(FLOAT_KEYWORD),
                            one(BYTE_KEYWORD),
                            one(LONG_KEYWORD)),
                            one(TYPE), O_FIELD, P_CLASS),
                    seq(one(AT), one(ANNOTATION), O_MODIFIER_LIST,
                            maybe(FIELD), P_CLASS),
                    seq(WS,
                            or(
                                    seq(O_FIELD, P_CLASS),
                                    seq(maybe(PARAMETER), one(PARAMETER_LIST),
                                            one(METHOD), P_CLASS),
                                    seq(maybe(MODIFIER_LIST), any(CLASS))))),
            one(JAVA_FILE),
            not(prevChar(Character::isJavaIdentifierPart)));
}
