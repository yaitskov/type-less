package org.dan.idea.charremap.key;

import static com.intellij.ide.highlighter.JavaFileType.INSTANCE;
import static org.dan.idea.charremap.MapperAdapter.logPaths;
import static org.slf4j.LoggerFactory.getLogger;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import org.dan.idea.charremap.mock.MockIde;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public interface Mix {
    Logger logger = getLogger(Mix.class);

    @NotNull
    default MockIde makeIde(char c, int offset, String text) {
        final PsiFile pf = PsiFileFactory.getInstance(getProject())
                .createFileFromText("A.java", INSTANCE, text);
        final ASTNode node = pf.findElementAt(offset).getNode();
        logPaths(c, node);
        return new MockIde(text, offset, node, c);
    }

    Project getProject();
}
