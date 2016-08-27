package org.dan.idea.charremap.mock;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.ide.impl.DataManagerImpl.MyDataContext;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import org.dan.idea.charremap.RootMapper;
import org.dan.idea.charremap.key.MatcherMapper;

public class AtMatcherTest extends LightPlatformCodeInsightFixtureTestCase {
    public void testTopClassNoAnnotations() {
        String text = "\n\nclass A {}\n";
        PsiFile pf = PsiFileFactory.getInstance(getProject())
                .createFileFromText("A.java", JavaFileType.INSTANCE,
                        text);

//        RecordingTypeHandler recorder = new RecordingTypeHandler();
//        new RootMapper(MatcherMapper.CHAR_MAP)
//                .apply(new MockEditor(new MockDocument(text),
//                        new MockCaretModel(1)), '2', );
    }
}
