package org.dan.idea.charremap.matcher;

import static com.intellij.psi.JavaTokenType.AT;
import static com.intellij.psi.JavaTokenType.BOOLEAN_KEYWORD;
import static com.intellij.psi.JavaTokenType.BYTE_KEYWORD;
import static com.intellij.psi.JavaTokenType.CLASS_KEYWORD;
import static com.intellij.psi.JavaTokenType.DOUBLE_KEYWORD;
import static com.intellij.psi.JavaTokenType.EQ;
import static com.intellij.psi.JavaTokenType.FINAL_KEYWORD;
import static com.intellij.psi.JavaTokenType.FLOAT_KEYWORD;
import static com.intellij.psi.JavaTokenType.IDENTIFIER;
import static com.intellij.psi.JavaTokenType.INT_KEYWORD;
import static com.intellij.psi.JavaTokenType.LONG_KEYWORD;
import static com.intellij.psi.JavaTokenType.PRIVATE_KEYWORD;
import static com.intellij.psi.JavaTokenType.PROTECTED_KEYWORD;
import static com.intellij.psi.JavaTokenType.PUBLIC_KEYWORD;
import static com.intellij.psi.JavaTokenType.RBRACE;
import static com.intellij.psi.JavaTokenType.RPARENTH;
import static com.intellij.psi.JavaTokenType.STATIC_KEYWORD;
import static com.intellij.psi.JavaTokenType.VOID_KEYWORD;
import static com.intellij.psi.JavaTokenType.VOLATILE_KEYWORD;
import static com.intellij.psi.TokenType.ERROR_ELEMENT;
import static com.intellij.psi.impl.java.stubs.JavaStubElementTypes.JAVA_FILE;
import static com.intellij.psi.impl.source.tree.JavaElementType.ANNOTATION;
import static com.intellij.psi.impl.source.tree.JavaElementType.ANONYMOUS_CLASS;
import static com.intellij.psi.impl.source.tree.JavaElementType.CLASS;
import static com.intellij.psi.impl.source.tree.JavaElementType.CODE_BLOCK;
import static com.intellij.psi.impl.source.tree.JavaElementType.DECLARATION_STATEMENT;
import static com.intellij.psi.impl.source.tree.JavaElementType.EXPRESSION_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.FIELD;
import static com.intellij.psi.impl.source.tree.JavaElementType.JAVA_CODE_REFERENCE;
import static com.intellij.psi.impl.source.tree.JavaElementType.LAMBDA_EXPRESSION;
import static com.intellij.psi.impl.source.tree.JavaElementType.METHOD;
import static com.intellij.psi.impl.source.tree.JavaElementType.MODIFIER_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.NEW_EXPRESSION;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER;
import static com.intellij.psi.impl.source.tree.JavaElementType.PARAMETER_LIST;
import static com.intellij.psi.impl.source.tree.JavaElementType.RETURN_STATEMENT;
import static com.intellij.psi.impl.source.tree.JavaElementType.TYPE;
import static org.dan.idea.charremap.composite.Any.any;
import static org.dan.idea.charremap.composite.Backward.backward;
import static org.dan.idea.charremap.composite.LastChild.lastChild;
import static org.dan.idea.charremap.composite.LookAhead.lookAhead;
import static org.dan.idea.charremap.composite.Maybe.maybe;
import static org.dan.idea.charremap.composite.Not.not;
import static org.dan.idea.charremap.composite.One.WS;
import static org.dan.idea.charremap.composite.One.one;
import static org.dan.idea.charremap.composite.Or.or;
import static org.dan.idea.charremap.composite.OriginNode.originNode;
import static org.dan.idea.charremap.composite.Plus.plus;
import static org.dan.idea.charremap.composite.PrevChar.prevChar;
import static org.dan.idea.charremap.composite.PrevSibling.prevSibling;
import static org.dan.idea.charremap.composite.Seq.seq;

import org.dan.idea.charremap.composite.Maybe;
import org.dan.idea.charremap.composite.One;
import org.dan.idea.charremap.composite.Or;
import org.dan.idea.charremap.composite.Plus;
import org.dan.idea.charremap.composite.Seq;

public class Matchers {
    private static final Plus P_CLASS = plus(CLASS);
    private static final One O_FIELD = one(FIELD);
    private static final One O_MODIFIER_LIST = one(MODIFIER_LIST);
    private static final One O_METHOD = one(METHOD);
    private static final Or METHOD_OR_FIELD = or(O_FIELD, O_METHOD);
    private static final One O_PARAM_LIST = one(PARAMETER_LIST);
    private static final Seq PAR_PAR_LIST_METHOD = seq(one(PARAMETER),
            O_PARAM_LIST, O_METHOD);
    private static final One O_C_BLOCK = one(CODE_BLOCK);
    private static final Seq RETURN_CODE_METHOD = seq(one(RETURN_STATEMENT),
            O_C_BLOCK, O_METHOD);
    private static final One O_NEW_EXPRESSION = one(NEW_EXPRESSION);
    private static final Seq ANONYMOUS = seq(one(ANONYMOUS_CLASS),
            maybe(seq(O_NEW_EXPRESSION, one(EXPRESSION_LIST))),
            O_NEW_EXPRESSION, RETURN_CODE_METHOD);
    private static final Maybe M_ANONYMOUS = Maybe.maybe(ANONYMOUS);

    public static Matcher AT_MATCHER = seq(
            or(
                    seq(one(RBRACE), ANONYMOUS, P_CLASS),
                    seq(one(RPARENTH), O_PARAM_LIST, O_METHOD,
                            M_ANONYMOUS, P_CLASS),
                    seq(one(CLASS_KEYWORD), one(CLASS),
                            or(
                                    seq(one(DECLARATION_STATEMENT), O_C_BLOCK,
                                            one(LAMBDA_EXPRESSION),
                                            RETURN_CODE_METHOD),
                                    ANONYMOUS),
                            P_CLASS),
                    seq(or(one(PRIVATE_KEYWORD),
                            one(PUBLIC_KEYWORD),
                            one(PROTECTED_KEYWORD),
                            one(VOLATILE_KEYWORD),
                            one(STATIC_KEYWORD),
                            one(FINAL_KEYWORD)),
                            O_MODIFIER_LIST,
                            Maybe.maybe(or(PAR_PAR_LIST_METHOD,
                                    O_FIELD,
                                    seq(O_METHOD, M_ANONYMOUS))), P_CLASS),
                    seq(or(
                            seq(one(IDENTIFIER), one(JAVA_CODE_REFERENCE)),
                            one(INT_KEYWORD),
                            one(DOUBLE_KEYWORD),
                            one(BOOLEAN_KEYWORD),
                            one(FLOAT_KEYWORD),
                            one(BYTE_KEYWORD),
                            one(VOID_KEYWORD),
                            one(LONG_KEYWORD)),
                            plus(TYPE),
                            maybe(or(seq(PAR_PAR_LIST_METHOD, M_ANONYMOUS),
                                    seq(O_METHOD, M_ANONYMOUS),
                                    O_FIELD)),
                            P_CLASS),
                    seq(one(AT), one(ANNOTATION), O_MODIFIER_LIST,
                            Maybe.maybe(or(O_FIELD, seq(PAR_PAR_LIST_METHOD, M_ANONYMOUS))),
                            P_CLASS),
                    seq(WS,
                            or(
                                    seq(ANONYMOUS, P_CLASS),
                                    seq(O_FIELD, P_CLASS),
                                    seq(maybe(PARAMETER), O_PARAM_LIST,
                                            O_METHOD, P_CLASS),
                                    seq(or(seq(O_MODIFIER_LIST, maybe(METHOD_OR_FIELD)),
                                            not(originNode(prevSibling(lastChild(
                                                    backward(one(ERROR_ELEMENT), lookAhead(one(EQ)))))))),
                                            any(CLASS))))),
            one(JAVA_FILE),
            not(prevChar(Character::isJavaIdentifierPart)));

    public static Matcher LESS_MATCHER = seq(
//            or(
            seq(WS, or(
                    seq(O_C_BLOCK, O_METHOD),
                    O_FIELD)),//),
            P_CLASS,
            O_FILE);
}
