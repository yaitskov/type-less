package org.dan.idea.charremap;

import static com.intellij.openapi.actionSystem.CommonDataKeys.PSI_FILE;
import static java.util.Optional.of;
import static org.dan.idea.charremap.key.MatcherMapper.types;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import com.intellij.lang.ASTNode;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class MapperAdapter implements TypedActionHandler {
    private static final Logger logger = getLogger(MapperAdapter.class);
    private final Mapper mapper;
    private final TypedActionHandler forward;

    public MapperAdapter(Mapper mapper, TypedActionHandler forward) {
        this.mapper = mapper;
        this.forward = forward;
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dc) {
        final PsiFile pf = dc.getData(PSI_FILE);
        if (pf != null && pf.getLanguage().is(JavaLanguage.INSTANCE)) {
            process(pf, editor, c).map(cc -> {
                forward.execute(editor, cc, dc);
                return null;
            });
        } else {
            forward.execute(editor, c, dc);
        }
    }

    private Optional<Character> process(PsiFile pf, @NotNull Editor editor, char c) {
        PsiElement root = pf.getNode().getPsi();
        final int offset = editor.getCaretModel().getOffset();
        PsiElement elementAt = root.findElementAt(offset);
        if (elementAt == null) {
            logger.error("No psi element at position {}", offset);
            return of(c);
        }
        final ASTNode node = elementAt.getNode();
        IdeFacade ide = new IdeFacadeImpl(c, node, editor);
        logPaths(c, node);
        return mapper.apply(ide);
    }

    public static void logPaths(char c, ASTNode node) {
        logger.info("{}) current [{}]\nnext [{}]\nprev [{}]",
                c,
                types(node),
                types(node.getTreeNext()),
                types(node.getTreePrev()));
    }
}
