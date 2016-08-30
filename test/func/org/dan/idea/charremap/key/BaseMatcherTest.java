package org.dan.idea.charremap.key;

import static com.intellij.ide.highlighter.JavaFileType.INSTANCE;
import static org.dan.idea.charremap.MapperAdapter.logPaths;
import static org.slf4j.LoggerFactory.getLogger;

import com.intellij.lang.ASTNode;
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
        final PsiFile pf = PsiFileFactory.getInstance(getProject())
                .createFileFromText("A.java", INSTANCE, text);
        final ASTNode node = pf.findElementAt(offset).getNode();
        logPaths(c, node);
        return new MockIde(text, offset, node, c);
    }

    protected void checkYes(int offset, String text) {
        check(in, out, offset, text);
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
