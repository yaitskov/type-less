package org.dan.idea.charremap.key;

import static com.intellij.ide.highlighter.JavaFileType.INSTANCE;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.dan.idea.charremap.Mappers;
import org.dan.idea.charremap.RootMapper;
import org.dan.idea.charremap.mock.MockIde;
import org.jetbrains.annotations.NotNull;

public class AtMatcherTest extends LightPlatformCodeInsightFixtureTestCase {
    public void testTopClassNoAnnotations() {
        check(1, "\n\nclass A {}\n");
    }

    @NotNull
    private MockIde makeIde(char c, int offset, String text) {
        PsiFile pf = PsiFileFactory.getInstance(getProject())
                .createFileFromText("A.java", INSTANCE, text);
        return new MockIde(text, offset, pf.findElementAt(offset).getNode(), c);
    }

    private void check(int offset, String text) {
        assertEquals('@', new RootMapper(Mappers.CHAR_MAP)
                .apply(makeIde('2', offset, text))
                .get()
                .charValue());
    }
}
