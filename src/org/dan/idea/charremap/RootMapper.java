package org.dan.idea.charremap;

import static java.lang.Character.isLetter;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toUpperCase;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Map;
import java.util.Optional;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.editor.LogicalPosition;
import org.slf4j.Logger;

public class RootMapper implements Mapper {
    private static final Logger logger = getLogger(RootMapper.class);
    private final Map<Character, Mapper> charMap;

    public RootMapper(Map<Character, Mapper> charMap) {
        this.charMap = charMap;
    }

    public Optional<Character> apply(IdeFacade ide) {
        final Mapper mapper = charMap.getOrDefault(ide.eventChar(),
                (ec) -> of(ide.eventChar()));

        final Optional<Character> mayBeMapped = mapper.apply(ide);
        if (!mayBeMapped.isPresent()) {
            return empty();
        }
        final char mappedC = mayBeMapped.get();
        final int offset = ide.offset();
        if (mappedC != ide.eventChar()) {
            logger.info("Map character [{}] => [{}] at offset [{}]",
                    ide.eventChar(), mappedC, offset);
        }
        if (offset > 0) {
            final String content = ide.text();
            final char prevChar = content.charAt(offset - 1);
            if (prevChar == '@') {
                if (isLowerCase(mappedC)) {
                    return of(toUpperCase(mappedC));
                } else if (isUpperCase(mappedC)) {
                    showInfo("Redundant SHIFT key pressed",
                            "Letters right after '@' are upcased automatically");
                }
            }
            if (mappedC == '=' && prevChar == '1') {
                ide.update(w -> w.replace(offset - 1, offset, "!= ")
                        .moveTo(offset + 2));
                return empty();
            }
            if (mappedC == '.' && prevChar == '-') {
                ide.update(w -> w.replace(offset - 1, offset, "-> ")
                        .moveTo(offset + 2));
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
            if (mappedC == '.'
                    && isLetter(prevChar)) {
                final LogicalPosition lpos = ide.offsetToLogical(offset);
                if (content.substring(offset - lpos.column, offset).contains("<")) {
                    return of('>');
                }
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
