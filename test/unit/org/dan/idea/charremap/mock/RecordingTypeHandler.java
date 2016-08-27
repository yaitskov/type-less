package org.dan.idea.charremap.mock;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import org.jetbrains.annotations.NotNull;

public class RecordingTypeHandler implements TypedActionHandler {
    private String recorded = "";

    @Override
    public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
        recorded += c;
    }

    public String getRecorded() {
        return recorded;
    }
}
