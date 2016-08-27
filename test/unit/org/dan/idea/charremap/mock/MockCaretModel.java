package org.dan.idea.charremap.mock;

import java.util.List;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.CaretState;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.editor.markup.TextAttributes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MockCaretModel implements CaretModel {
    private final int offset;

    public MockCaretModel(int offset) {
        this.offset = offset;
    }

    @Override
    public void moveCaretRelatively(int i, int i1, boolean b, boolean b1, boolean b2) {

    }

    @Override
    public void moveToLogicalPosition(@NotNull LogicalPosition logicalPosition) {

    }

    @Override
    public void moveToVisualPosition(@NotNull VisualPosition visualPosition) {

    }

    @Override
    public void moveToOffset(int i) {

    }

    @Override
    public void moveToOffset(int i, boolean b) {

    }

    @Override
    public boolean isUpToDate() {
        return false;
    }

    @NotNull
    @Override
    public LogicalPosition getLogicalPosition() {
        return null;
    }

    @NotNull
    @Override
    public VisualPosition getVisualPosition() {
        return null;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public void addCaretListener(@NotNull CaretListener caretListener) {

    }

    @Override
    public void removeCaretListener(@NotNull CaretListener caretListener) {

    }

    @Override
    public int getVisualLineStart() {
        return 0;
    }

    @Override
    public int getVisualLineEnd() {
        return 0;
    }

    @Override
    public TextAttributes getTextAttributes() {
        return null;
    }

    @Override
    public boolean supportsMultipleCarets() {
        return false;
    }

    @NotNull
    @Override
    public Caret getCurrentCaret() {
        return null;
    }

    @NotNull
    @Override
    public Caret getPrimaryCaret() {
        return null;
    }

    @Override
    public int getCaretCount() {
        return 0;
    }

    @NotNull
    @Override
    public List<Caret> getAllCarets() {
        return null;
    }

    @Nullable
    @Override
    public Caret getCaretAt(@NotNull VisualPosition visualPosition) {
        return null;
    }

    @Nullable
    @Override
    public Caret addCaret(@NotNull VisualPosition visualPosition) {
        return null;
    }

    @Nullable
    @Override
    public Caret addCaret(@NotNull VisualPosition visualPosition, boolean b) {
        return null;
    }

    @Override
    public boolean removeCaret(@NotNull Caret caret) {
        return false;
    }

    @Override
    public void removeSecondaryCarets() {

    }

    @Override
    public void setCaretsAndSelections(@NotNull List<CaretState> list) {

    }

    @Override
    public void setCaretsAndSelections(@NotNull List<CaretState> list, boolean b) {

    }

    @NotNull
    @Override
    public List<CaretState> getCaretsAndSelections() {
        return null;
    }

    @Override
    public void runForEachCaret(@NotNull CaretAction caretAction) {

    }

    @Override
    public void runForEachCaret(@NotNull CaretAction caretAction, boolean b) {

    }

    @Override
    public void runBatchCaretOperation(@NotNull Runnable runnable) {

    }
}
