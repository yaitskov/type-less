package org.dan.idea.charremap;

import java.util.HashMap;
import java.util.Map;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;


public class EntryPoint extends AnAction {
    private static final Logger logger = Logger.getInstance(EntryPoint.class);

    static {
        logger.info("Init Type-Less");
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        Map<Character, Character> charMap = new HashMap<>();
        new CharMapBuilder(charMap)
                .add('2', '@')
                .add('[', '}')
                .add(']', '}')
                .add('0', ')')
                .add('9', '(')
                .add('\'', '"');
        typedAction.setupHandler(new CharRemapTypeHandler(charMap));
    }

    @Override
    public void actionPerformed(final AnActionEvent anActionEvent) {
    }

    @Override
    public void update(final AnActionEvent e) {
    }
}
