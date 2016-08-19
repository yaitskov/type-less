package com.dan;

import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.KeyStroke;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTypeHandler implements TypedActionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyTypeHandler.class);
    private final Map<Character, Character> charMap;

    public MyTypeHandler(Map<Character, Character> charMap) {
        this.charMap = charMap;
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        KeyStroke ks = KeyStroke.getKeyStroke(c);
        boolean shiftPressed = (ks.getModifiers() & KeyEvent.SHIFT_DOWN_MASK) != 0;
        logger.info("Type action with character [{}]. Shift is pressed ({}) {} {}",
                c, shiftPressed, ks.getModifiers(), ks.getKeyCode());
        final Document document = editor.getDocument();
        Project project = editor.getProject();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int offset = editor.getCaretModel().getOffset();
                logger.info("Insert at offset {}", offset);
                if (c == '2') {
                    document.insertString(offset, "@");
                } else if (c == '@') {
                    document.insertString(offset, "2");
                } else {
                    document.insertString(offset, String.valueOf(c));
                }
            }
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }
}
