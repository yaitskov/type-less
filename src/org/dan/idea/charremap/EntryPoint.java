package org.dan.idea.charremap;

import java.util.HashMap;
import java.util.Map;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EntryPoint extends AnAction {
    private static final Logger logger = LoggerFactory.getLogger(CharRemapTypeHandler.class);

    static {
        com.intellij.openapi.diagnostic.Logger.getInstance(EntryPoint.class).info("Init logging");
        logger.info("Init Type-Less");
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        Map<Character, Character> charMap = new HashMap<>();
        new CharMapBuilder(charMap)
                .add('2', '@')
                .add('[', '{')
                .add(']', '}')
                .add('0', ')')
                .add('-', '_')
                .add('9', '(')
                .add('\'', '"');
        TypedActionHandler forward = typedAction.getHandler();
        logger.info("Decorate handler {}", forward);
        typedAction.setupHandler(new CharRemapTypeHandler(charMap, forward));
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
