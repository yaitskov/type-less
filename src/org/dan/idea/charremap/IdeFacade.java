package org.dan.idea.charremap;

import java.util.function.Consumer;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.LogicalPosition;

public interface IdeFacade {
    LogicalPosition offsetToLogical(int offset);
    String text();
    int offset();
    void update(Consumer<Writer> updater);
    char eventChar();
    ASTNode eventNode();
}
