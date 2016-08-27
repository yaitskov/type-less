package org.dan.idea.charremap.mock;

import java.beans.PropertyChangeListener;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MockDocument implements Document {
    @NotNull
    private final String text;

    public MockDocument(@NotNull String text) {
        this.text = text;
    }

    @NotNull
    @Override
    public String getText() {
        return text;
    }

    @NotNull
    @Override
    public String getText(@NotNull TextRange textRange) {
        return text.substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public CharSequence getCharsSequence() {
        return text;
    }

    @NotNull
    @Override
    public CharSequence getImmutableCharSequence() {
        return null;
    }

    @NotNull
    @Override
    public char[] getChars() {
        return new char[0];
    }

    @Override
    public int getTextLength() {
        return 0;
    }

    @Override
    public int getLineCount() {
        return 0;
    }

    @Override
    public int getLineNumber(int i) {
        return 0;
    }

    @Override
    public int getLineStartOffset(int i) {
        return 0;
    }

    @Override
    public int getLineEndOffset(int i) {
        return 0;
    }

    @Override
    public void insertString(int i, @NotNull CharSequence charSequence) {
        throw new IllegalStateException();
    }

    @Override
    public void deleteString(int i, int i1) {
        throw new IllegalStateException();
    }

    @Override
    public void replaceString(int i, int i1, @NotNull CharSequence charSequence) {
        throw new IllegalStateException();
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public long getModificationStamp() {
        return 0;
    }

    @Override
    public void fireReadOnlyModificationAttempt() {

    }

    @Override
    public void addDocumentListener(@NotNull DocumentListener documentListener) {

    }

    @Override
    public void addDocumentListener(@NotNull DocumentListener documentListener, @NotNull Disposable disposable) {

    }

    @Override
    public void removeDocumentListener(@NotNull DocumentListener documentListener) {

    }

    @NotNull
    @Override
    public RangeMarker createRangeMarker(int i, int i1) {
        return null;
    }

    @NotNull
    @Override
    public RangeMarker createRangeMarker(int i, int i1, boolean b) {
        return null;
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener propertyChangeListener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener propertyChangeListener) {

    }

    @Override
    public void setReadOnly(boolean b) {

    }

    @NotNull
    @Override
    public RangeMarker createGuardedBlock(int i, int i1) {
        return null;
    }

    @Override
    public void removeGuardedBlock(@NotNull RangeMarker rangeMarker) {

    }

    @Nullable
    @Override
    public RangeMarker getOffsetGuard(int i) {
        return null;
    }

    @Nullable
    @Override
    public RangeMarker getRangeGuard(int i, int i1) {
        return null;
    }

    @Override
    public void startGuardedBlockChecking() {

    }

    @Override
    public void stopGuardedBlockChecking() {

    }

    @Override
    public void setCyclicBufferSize(int i) {

    }

    @Override
    public void setText(@NotNull CharSequence charSequence) {

    }

    @NotNull
    @Override
    public RangeMarker createRangeMarker(@NotNull TextRange textRange) {
        return null;
    }

    @Override
    public int getLineSeparatorLength(int i) {
        return 0;
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
