package org.dan.idea.charremap.mock;

import static org.dan.idea.charremap.mock.NoWriter.NO_WRITER;

import java.util.function.Consumer;
import java.util.function.Function;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.LogicalPosition;
import org.dan.idea.charremap.IdeFacade;
import org.dan.idea.charremap.Writer;

public class MockIde implements IdeFacade {
    private final String text;
    private final int offset;
    private final ASTNode node;
    private final char eventChar;
    private final Writer writer;
    private final Function<Integer, LogicalPosition> logPosMapper;

    public MockIde(String text, int offset, ASTNode node,
            char eventChar, Writer writer,
            Function<Integer, LogicalPosition> logPosMapper) {
        this.text = text;
        this.offset = offset;
        this.node = node;
        this.eventChar = eventChar;
        this.writer = writer;
        this.logPosMapper = logPosMapper;
    }

    public MockIde(String text, int offset, ASTNode node, char eventChar) {
        this(text, offset, node, eventChar, NO_WRITER,
                o -> { throw new IllegalStateException(); });
    }

    @Override
    public LogicalPosition offsetToLogical(int offset) {
        return logPosMapper.apply(offset);
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public int offset() {
        return offset;
    }

    @Override
    public void update(Consumer<Writer> updater) {
        updater.accept(writer);
    }

    @Override
    public char eventChar() {
        return eventChar;
    }

    @Override
    public ASTNode eventNode() {
        return node;
    }
}
