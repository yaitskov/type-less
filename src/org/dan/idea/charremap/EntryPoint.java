package org.dan.idea.charremap;

import static org.dan.idea.charremap.Mappers.CHAR_MAP;
import static org.slf4j.LoggerFactory.getLogger;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.slf4j.Logger;


public class EntryPoint extends AnAction {
    private static final Logger logger = getLogger(RootMapper.class);

    static {
        com.intellij.openapi.diagnostic.Logger
                .getInstance(EntryPoint.class).info("Init logging");
        logger.info("Init Type-Less");
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        TypedActionHandler forward = typedAction.getHandler();
        logger.info("Decorate handler {}", forward);
        typedAction.setupHandler(
                new MapperAdapter(
                        new RootMapper(CHAR_MAP),
                        forward));
    }

    @Override
    public void actionPerformed(final AnActionEvent anActionEvent) {
        // is not called
    }

    @Override
    public void update(final AnActionEvent anActionEvent) {
        // is not called
    }
}
