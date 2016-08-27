package org.dan.idea.charremap;

import static com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction;

import java.util.function.Consumer;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.LogicalPosition;

public class IdeFacadeImpl implements IdeFacade {
    private final char evenChar;
    private final ASTNode eventNode;
    private final Editor editor;

    private class WriterImpl implements Writer {
        @Override
        public Writer replace(int start, int end, String text) {
            editor.getDocument().replaceString(start, end, text);
            return this;
        }

        @Override
        public Writer moveTo(int offset) {
            editor.getCaretModel().moveToOffset(offset);
            return this;
        }
    }

    public IdeFacadeImpl(char evenChar, ASTNode eventNode, Editor editor) {
        this.evenChar = evenChar;
        this.eventNode = eventNode;
        this.editor = editor;
    }

    @Override
    public LogicalPosition offsetToLogical(int offset) {
        return editor.offsetToLogicalPosition(offset);
    }

    @Override
    public String text() {
        return editor.getDocument().getText();
    }

    @Override
    public int offset() {
        return editor.getCaretModel().getOffset();
    }

    @Override
    public void update(Consumer<Writer> updater) {
        runWriteCommandAction(editor.getProject(),
                () -> updater.accept(new WriterImpl()));
    }

    @Override
    public char eventChar() {
        return evenChar;
    }

    @Override
    public ASTNode eventNode() {
        return eventNode;
    }
}
