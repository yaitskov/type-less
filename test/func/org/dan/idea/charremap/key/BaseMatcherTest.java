package org.dan.idea.charremap.key;

import com.intellij.openapi.project.Project;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.dan.idea.charremap.Mappers;
import org.dan.idea.charremap.RootMapper;

public class BaseMatcherTest extends LightPlatformCodeInsightFixtureTestCase implements Mix {
    private final char in;
    private final char out;

    protected BaseMatcherTest(char in, char out) {
        this.in = in;
        this.out = out;
    }

    protected void checkYes(int offset, String text) {
        check(in, out, offset, text);
    }

    protected void checkNo(int offset, String text) {
        check(in, in, offset, text);
    }

    protected void check(char in, char out, int offset, String text) {
        assertEquals(out, new RootMapper(Mappers.CHAR_MAP)
                .apply(makeIde(in, offset, text))
                .get()
                .charValue());
    }

    public Project getProject() {
        return super.getProject();
    }
}
