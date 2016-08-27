package org.dan.idea.charremap.key;

import static com.intellij.ide.highlighter.JavaFileType.INSTANCE;
import static org.slf4j.LoggerFactory.getLogger;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.dan.idea.charremap.Mappers;
import org.dan.idea.charremap.RootMapper;
import org.dan.idea.charremap.mock.MockIde;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class BaseMatcherTest extends LightPlatformCodeInsightFixtureTestCase {
    protected static final Logger logger = getLogger(BaseMatcherTest.class);

    private final char in;
    private final char out;

    protected BaseMatcherTest(char in, char out) {
        this.in = in;
        this.out = out;
    }

    @NotNull
    protected MockIde makeIde(char c, int offset, String text) {
        PsiFile pf = PsiFileFactory.getInstance(getProject())
                .createFileFromText("A.java", INSTANCE, text);
        return new MockIde(text, offset, pf.findElementAt(offset).getNode(), c);
    }

    protected void checkYes(int offset, String text) {
        check('2', '@', offset, text);
    }

    protected void checkNo(int offset, String text) {
        check('2', '2', offset, text);
    }

    protected void check(char in, char out, int offset, String text) {
        assertEquals(out, new RootMapper(Mappers.CHAR_MAP)
                .apply(makeIde(in, offset, text))
                .get()
                .charValue());
    }
}
