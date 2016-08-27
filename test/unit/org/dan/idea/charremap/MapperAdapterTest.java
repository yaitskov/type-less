package org.dan.idea.charremap;

import static com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;

import com.intellij.lang.ASTNode;
import com.intellij.lang.FileASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.dan.idea.charremap.mock.MockDataContext;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapperAdapterTest {
    public static final int OFFSET = 1;
    char forwardedChar;
    char passedChar;

    IMocksControl control;
    PsiFile pf;

    @Before
    public void setUp() {
        control = EasyMock.createControl();
        pf = control.createMock(PsiFile.class);
    }

    @After
    public void tearDown() {
        control.verify();
    }

    @Test
    public void passJavaFile() {
        Editor editor = editor();
        control.replay();

        MockDataContext dc = new MockDataContext();
        dc.setData(PSI_FILE, pf);
        new MapperAdapter(
                ide -> {
                    passedChar = ide.eventChar();
                    return empty();
                },
                (e, c, _dc) -> {
                    throw new IllegalStateException();
                }).execute(editor, 'x', dc);
    }

    @Test
    public void passAndForwardChar() {
        Editor editor = editor();
        control.replay();

        MockDataContext dc = new MockDataContext();
        dc.setData(PSI_FILE, pf);
        new MapperAdapter(
                ide -> of(passedChar = ide.eventChar()),
                (e, c, _dc) -> forwardedChar = c)
                .execute(editor, 'x', dc);
        assertEquals('x', forwardedChar);
        assertEquals('x', passedChar);
    }

    @Test
    public void forwardForNonJava() {
        Editor editor = control.createMock(Editor.class);
        expect(pf.getLanguage()).andReturn(XMLLanguage.INSTANCE);
        control.replay();
        MockDataContext dc = new MockDataContext();
        dc.setData(PSI_FILE, pf);
        new MapperAdapter(
                ide -> {
                    throw new IllegalStateException();
                },
                (e, c, _dc) -> forwardedChar = c)
                .execute(editor, 'y', dc);
        assertEquals('y', forwardedChar);
    }

    @NotNull
    private Editor editor() {
        Editor editor = control.createMock(Editor.class);
        CaretModel caretModel = control.createMock(CaretModel.class);
        expect(pf.getLanguage()).andReturn(JavaLanguage.INSTANCE);
        expect(editor.getCaretModel()).andReturn(caretModel);
        expect(caretModel.getOffset()).andReturn(OFFSET);
        FileASTNode file = control.createMock(FileASTNode.class);
        expect(pf.getNode()).andReturn(file);
        PsiElement root = control.createMock(PsiElement.class);
        expect(file.getPsi()).andReturn(root);
        PsiElement current = control.createMock(PsiElement.class);
        expect(root.findElementAt(OFFSET)).andReturn(current);
        ASTNode ast = control.createMock(ASTNode.class);
        expect(current.getNode()).andReturn(ast);

        expect(ast.getElementType()).andReturn(WHITE_SPACE);
        expect(ast.getTreeParent()).andReturn(null);
        expect(ast.getTreeNext()).andReturn(null);
        expect(ast.getTreePrev()).andReturn(null);
        return editor;
    }
}
