package org.dan.idea.charremap;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;

public class CharEvent {
    public final char origin;
    public final DataContext dc;
    public final Editor editor;
    public final PsiFile pf;

    public CharEvent(char origin, DataContext dc, Editor editor, PsiFile pf) {
        this.origin = origin;
        this.dc = dc;
        this.editor = editor;
        this.pf = pf;
    }
}
