package org.dan.idea.charremap;

import static com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction;
import static java.lang.Character.isLetter;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toUpperCase;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Map;
import java.util.Optional;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharRemapTypeHandler implements TypedActionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CharRemapTypeHandler.class);
    private final Map<Character, Mapper> charMap;
    private final TypedActionHandler forward;

    public CharRemapTypeHandler(Map<Character, Mapper> charMap, TypedActionHandler forward) {
        this.charMap = charMap;
        this.forward = forward;
    }

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dc) {
        PsiFile pf = dc.getData(PlatformDataKeys.PSI_FILE);
        if (pf.getLanguage().is(JavaLanguage.INSTANCE)) {
            final Optional<Character> mapped = map(new CharEvent(c, dc, editor, pf));
            if (mapped.isPresent()) {
                forward.execute(editor, mapped.get(), dc);
            }
        } else {
            forward.execute(editor, c, dc);
        }
    }

    private Optional<Character> map(CharEvent ce) {
        final Mapper mapper = charMap.getOrDefault(ce.origin, (ec) -> of(ce.origin));
        final Optional<Character> mayBeMapped = mapper.apply(ce);
        if (!mayBeMapped.isPresent()) {
            return mayBeMapped;
        }
        final char mappedC = mayBeMapped.get();
        Editor editor = ce.editor;
        CaretModel caret = editor.getCaretModel();
        final int offset = caret.getOffset();
        final LogicalPosition lpos = editor.offsetToLogicalPosition(offset);
        if (mappedC != ce.origin) {
            logger.info("Map character [{}] => [{}] at offset [{}]", ce.origin, mappedC, offset);
        }
        final Document document = editor.getDocument();
        if (offset > 0) {
            final String content = document.getText();
            final char prevChar = content.charAt(offset - 1);
            if (prevChar == '@') {
                if (isLowerCase(mappedC)) {
                    return of(toUpperCase(mappedC));
                } else if (isUpperCase(mappedC)) {
                    showInfo("Redundant SHIFT key pressed",
                            "Letters right after '@' are upcased automatically");
                }
            }
            if (offset > 1) {
                char p2Char = content.charAt(offset - 2);
                if (mappedC == '2' && ((prevChar != ' ' && prevChar != '(') || p2Char != ' ')) {
                    return of('2');
                }
                if (mappedC == ' ' && prevChar == '<' && isLetter(p2Char)) {
                    runWriteCommandAction(
                            editor.getProject(),
                            () -> {
                                document.replaceString(offset - 1, offset, ", ");
                                caret.moveToOffset(offset + 1);
                            });
                    return empty();
                }
            }
            if (mappedC == '=' && prevChar == '1') {
                runWriteCommandAction(
                        editor.getProject(),
                        () -> {
                            document.replaceString(offset - 1, offset, "!= ");
                            caret.moveToOffset(offset + 2);
                        });
                return empty();
            }
            if (mappedC == '.' && prevChar == '-') {
                runWriteCommandAction(
                        editor.getProject(),
                        () -> {
                            document.replaceString(offset - 1, offset, "-> ");
                            caret.moveToOffset(offset + 2);
                        });
                return empty();
            }
            if (mappedC == '{' && isLetterOrDigit(prevChar)) {
                return of('[');
            }
            if (mappedC == '}' && prevChar == '[') {
                return of(']');
            }
            if (mappedC == '_' && (prevChar == ' ' || prevChar == ')')) {
                return of('-');
            }
            if (mappedC == ',' && isLetter(prevChar)) {
                return of('<');
            }
            if (mappedC == '.'
                    && isLetter(prevChar)
                    && content.substring(offset - lpos.column, offset).contains("<")) {
                return of('>');
            }
        }
        return of(mappedC);
    }

    private void showInfo(String title, String content) {
        Notifications.Bus.notify(new Notification("TypeLess",
                title, content,
                NotificationType.INFORMATION));
    }
}
