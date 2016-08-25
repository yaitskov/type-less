package org.dan.idea.charremap;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;

public class CharEvent {
    public final char origin;
    public final DataContext dc;
    public final Editor editor;

    public CharEvent(char origin, DataContext dc, Editor editor) {
        this.origin = origin;
        this.dc = dc;
        this.editor = editor;
    }
}
