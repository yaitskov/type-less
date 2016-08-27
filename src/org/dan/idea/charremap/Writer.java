package org.dan.idea.charremap;

public interface Writer {
    Writer replace(int start, int end, String text);
    Writer moveTo(int offset);
}
