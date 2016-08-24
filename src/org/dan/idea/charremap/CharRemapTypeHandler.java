package org.dan.idea.charremap;

import static com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction;
import static java.lang.Character.isLetter;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toUpperCase;

import java.util.Map;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharRemapTypeHandler implements TypedActionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CharRemapTypeHandler.class);
    private final Map<Character, Character> charMap;
    private final TypedActionHandler forward;

    public CharRemapTypeHandler(Map<Character, Character> charMap, TypedActionHandler forward) {
        this.charMap = charMap;
        this.forward = forward;
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        final char map = map(editor, c);
        if (map == 0) {
            return;
        }
        forward.execute(editor, map, dataContext);
    }

    private char map(@NotNull Editor editor, char c) {
        final char mappedC = charMap.getOrDefault(c, c);
        CaretModel caret = editor.getCaretModel();
        final int offset = caret.getOffset();
        final LogicalPosition lpos = editor.offsetToLogicalPosition(offset);
        if (mappedC != c) {
            logger.info("Map character [{}] => [{}] at offset [{}]", c, mappedC, offset);
        }
        final Document document = editor.getDocument();
        if (offset > 0) {
            final String content = document.getText();
            final char prevChar = content.charAt(offset - 1);
            if (prevChar == '@') {
                if (isLowerCase(mappedC)) {
                    return toUpperCase(mappedC);
                } else if (isUpperCase(mappedC)) {
                    showInfo("Redundant SHIFT key pressed",
                            "Letters right after '@' are upcased automatically");
                }
            }
            if (offset > 1) {
                char p2Char = content.charAt(offset - 2);
                if (mappedC == '2' && ((prevChar != ' ' && prevChar != '(') || p2Char != ' ')) {
                    return '2';
                }
                if (mappedC == ' ' && prevChar == '<' && isLetter(p2Char)) {
                    runWriteCommandAction(
                            editor.getProject(),
                            () -> {
                                document.replaceString(offset - 1, offset, ", ");
                                caret.moveToOffset(offset + 1);
                            });
                    return 0;
                }
            }
            if (mappedC == '=' && prevChar == '1') {
                runWriteCommandAction(
                        editor.getProject(),
                        () -> {
                            document.replaceString(offset - 1, offset, "!= ");
                            caret.moveToOffset(offset + 2);
                        });
                return 0;
            }
            if (mappedC == '.' && prevChar == '-') {
                runWriteCommandAction(
                        editor.getProject(),
                        () -> {
                            document.replaceString(offset - 1, offset, "-> ");
                            caret.moveToOffset(offset + 2);
                        });
                return 0;
            }
            if (mappedC == '{' && isLetterOrDigit(prevChar)) {
                return '[';
            }
            if (mappedC == '}' && prevChar == '[') {
                return ']';
            }
            if (mappedC == '_' && (prevChar == ' ' || prevChar == ')')) {
                return '-';
            }
            if (mappedC == ',' && isLetter(prevChar)) {
                return '<';
            }
            if (mappedC == '.'
                    && isLetter(prevChar)
                    && content.substring(offset - lpos.column, offset).contains("<")) {
                return '>';
            }
        }
        return mappedC;
    }

    private void showInfo(String title, String content) {
        Notifications.Bus.notify(new Notification("TypeLess",
                title, content,
                NotificationType.INFORMATION));
    }
}
