package org.dan.idea.charremap.mock;

import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.border.Border;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorGutter;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.FoldingModel;
import com.intellij.openapi.editor.IndentsModel;
import com.intellij.openapi.editor.LogicalPosition;
import com.intellij.openapi.editor.ScrollingModel;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.SoftWrapModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.event.EditorMouseEventArea;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MockEditor implements Editor {
    @NotNull
    private final Document document;

    @NotNull
    private final CaretModel caretModel;

    public MockEditor(@NotNull Document document, @NotNull CaretModel caretModel) {
        this.document = document;
        this.caretModel = caretModel;
    }

    @NotNull
    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public boolean isViewer() {
        return false;
    }

    @NotNull
    @Override
    public JComponent getComponent() {
        return null;
    }

    @NotNull
    @Override
    public JComponent getContentComponent() {
        return null;
    }

    @Override
    public void setBorder(@Nullable Border border) {

    }

    @Override
    public Insets getInsets() {
        return null;
    }

    @NotNull
    @Override
    public SelectionModel getSelectionModel() {
        return null;
    }

    @NotNull
    @Override
    public MarkupModel getMarkupModel() {
        return null;
    }

    @NotNull
    @Override
    public FoldingModel getFoldingModel() {
        return null;
    }

    @NotNull
    @Override
    public ScrollingModel getScrollingModel() {
        return null;
    }

    @NotNull
    @Override
    public CaretModel getCaretModel() {
        return caretModel;
    }

    @NotNull
    @Override
    public SoftWrapModel getSoftWrapModel() {
        return null;
    }

    @NotNull
    @Override
    public EditorSettings getSettings() {
        return null;
    }

    @NotNull
    @Override
    public EditorColorsScheme getColorsScheme() {
        return null;
    }

    @Override
    public int getLineHeight() {
        return 0;
    }

    @NotNull
    @Override
    public Point logicalPositionToXY(@NotNull LogicalPosition logicalPosition) {
        return null;
    }

    @Override
    public int logicalPositionToOffset(@NotNull LogicalPosition logicalPosition) {
        return 0;
    }

    @NotNull
    @Override
    public VisualPosition logicalToVisualPosition(@NotNull LogicalPosition logicalPosition) {
        return null;
    }

    @NotNull
    @Override
    public Point visualPositionToXY(@NotNull VisualPosition visualPosition) {
        return null;
    }

    @NotNull
    @Override
    public LogicalPosition visualToLogicalPosition(@NotNull VisualPosition visualPosition) {
        return null;
    }

    @NotNull
    @Override
    public LogicalPosition offsetToLogicalPosition(int i) {
        return new LogicalPosition(1, i + 1);
    }

    @NotNull
    @Override
    public VisualPosition offsetToVisualPosition(int i) {
        return null;
    }

    @NotNull
    @Override
    public VisualPosition offsetToVisualPosition(int i, boolean b, boolean b1) {
        return null;
    }

    @NotNull
    @Override
    public LogicalPosition xyToLogicalPosition(@NotNull Point point) {
        return null;
    }

    @NotNull
    @Override
    public VisualPosition xyToVisualPosition(@NotNull Point point) {
        return null;
    }

    @Override
    public void addEditorMouseListener(@NotNull EditorMouseListener editorMouseListener) {

    }

    @Override
    public void removeEditorMouseListener(@NotNull EditorMouseListener editorMouseListener) {

    }

    @Override
    public void addEditorMouseMotionListener(@NotNull EditorMouseMotionListener editorMouseMotionListener) {

    }

    @Override
    public void removeEditorMouseMotionListener(@NotNull EditorMouseMotionListener editorMouseMotionListener) {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Nullable
    @Override
    public Project getProject() {
        return null;
    }

    @Override
    public boolean isInsertMode() {
        return false;
    }

    @Override
    public boolean isColumnMode() {
        return false;
    }

    @Override
    public boolean isOneLineMode() {
        return false;
    }

    @NotNull
    @Override
    public EditorGutter getGutter() {
        return null;
    }

    @Nullable
    @Override
    public EditorMouseEventArea getMouseEventArea(@NotNull MouseEvent mouseEvent) {
        return null;
    }

    @Override
    public void setHeaderComponent(@Nullable JComponent jComponent) {

    }

    @Override
    public boolean hasHeaderComponent() {
        return false;
    }

    @Nullable
    @Override
    public JComponent getHeaderComponent() {
        return null;
    }

    @NotNull
    @Override
    public IndentsModel getIndentsModel() {
        return null;
    }

    @Nullable
    @Override
    public <T> T getUserData(@NotNull Key<T> key) {
        return null;
    }

    @Override
    public <T> void putUserData(@NotNull Key<T> key, @Nullable T t) {

    }
}
