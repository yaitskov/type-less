package org.dan.idea.charremap;

import static com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction;

import java.util.Map;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharRemapTypeHandler implements TypedActionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CharRemapTypeHandler.class);
    private final Map<Character, Character> charMap;

    public CharRemapTypeHandler(Map<Character, Character> charMap) {
        this.charMap = charMap;
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        final char mappedC = charMap.getOrDefault(c, c);
        final int offset = editor.getCaretModel().getOffset();
        if (mappedC != c) {
            logger.info("Map character [{}] => [{}] at offset [{}]", c, mappedC, offset);
        }
        final Document document = editor.getDocument();
        runWriteCommandAction(
                editor.getProject(),
                () -> {
                    document.insertString(offset, String.valueOf(mappedC));
                    editor.getCaretModel().moveToOffset(offset + 1);
                });
    }
}
