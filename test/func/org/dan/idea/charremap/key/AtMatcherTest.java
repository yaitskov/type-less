package org.dan.idea.charremap.key;

import static com.intellij.ide.highlighter.JavaFileType.INSTANCE;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.dan.idea.charremap.Mappers;
import org.dan.idea.charremap.RootMapper;
import org.dan.idea.charremap.mock.MockIde;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

public class AtMatcherTest extends LightPlatformCodeInsightFixtureTestCase {
    static {
        LoggerFactory.getLogger(AtMatcherTest.class).info("init 12312312");
    }

    public void testTopClassNoAnnotations() {
        checkYes(1, "\n\nclass A {}\n");
    }

    public void testTopClassNoAnnotationsFirstChar() {
        checkYes(0, "\n\nclass A {}\n");
    }

    public void testTopClassTouchIdAhead() {
        checkNo(0, "class A {}\n");
    }

    public void testTopClassTouchIdBack() {
        checkNo(5, "class A {}\n");
    }

    public void testTopClassPackage() {
        checkYes(10, "package x;\n\nclass A {}\n");
    }

    public void testTopClassImport() {
        checkYes(25, "import java.lang.String;\n\nclass A {}\n");
    }

    public void testTopClassPackageAndImport() {
        checkYes(36, "package x;\nimport java.lang.String;\n\nclass A {}\n");
    }

    public void testTopClassBeforeAnnotation() {
        checkYes(0, " @Deprecated\nclass A {}\n");
    }

    public void testTopClassAfterAnnotation() {
        checkYes(12, "@Deprecated \nclass A {}\n");
    }

    public void testTopClassTouchBeforeAnnotation() {
        checkYes(0, "@Deprecated \nclass A {}\n");
    }

    public void testFieldTouchBeforeAnnotation() {
        checkYes(10, "class A { @Deprecated int x; }\n");
    }

    public void testFieldNoAnnotationAroundSpaces() {
        checkYes(10, "class A {  int x; }\n");
    }

    public void testFieldNoAnnotationSpaceAfter() {
        checkYes(9, "class A { int x; }\n");
    }

    public void testFieldNoAnnotationTouchType() {
        checkYes(10, "class A { int x; }\n");
    }

    public void testMethodNoAnnotationAroundSpaces() {
        checkYes(10, "class A {  void f() {} }\n");
    }

    public void testSubClassFieldNoAnnotationTouchTypeAroundSpaces() {
        checkYes(20, "class A { class B {  int x; } }\n");
    }

    public void testFieldInAnnotation() {
        checkNo(11, "class A { @Deprecated int x; }\n");
    }

    public void testTopClassTouchAfterAnnotation() {
        checkNo(11, "@Deprecated\nclass A {}\n");
    }

    public void testTopClassInAnnotation() {
        checkNo(1, "@Deprecated\nclass A {}\n");
    }

    @NotNull
    private MockIde makeIde(char c, int offset, String text) {
        PsiFile pf = PsiFileFactory.getInstance(getProject())
                .createFileFromText("A.java", INSTANCE, text);
        return new MockIde(text, offset, pf.findElementAt(offset).getNode(), c);
    }

    private void checkYes(int offset, String text) {
        check('2', '@', offset, text);
    }

    private void checkNo(int offset, String text) {
        check('2', '2', offset, text);
    }

    private void check(char in, char out, int offset, String text) {
        assertEquals(out, new RootMapper(Mappers.CHAR_MAP)
                .apply(makeIde(in, offset, text))
                .get()
                .charValue());
    }
}
