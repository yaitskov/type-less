package org.dan.idea.charremap.mock;

import org.dan.idea.charremap.Writer;

public class NoWriter implements Writer {
    public static final NoWriter NO_WRITER = new NoWriter();

    @Override
    public Writer replace(int start, int end, String text) {
        throw new IllegalStateException();
    }

    @Override
    public Writer moveTo(int offset) {
        throw new IllegalStateException();
    }
}
