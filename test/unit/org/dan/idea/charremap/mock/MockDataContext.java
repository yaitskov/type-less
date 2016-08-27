package org.dan.idea.charremap.mock;

import java.util.HashMap;
import java.util.Map;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKey;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MockDataContext implements DataContext {
    private final Map map = new HashMap<>();

    @Nullable
    @Override
    public Object getData(@NonNls String s) {
        return null;
    }

    @Nullable
    @Override
    public <T> T getData(@NotNull DataKey<T> key) {
        return (T) map.get(key);
    }

    public <T> void setData(DataKey<T> key, T value) {
        map.put(key, value);
    }
}
