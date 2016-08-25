package org.dan.idea.charremap;

import static java.util.Optional.of;

import java.util.Map;

public class CharMapBuilder {
    private final Map<Character, Mapper> map;

    public CharMapBuilder(Map<Character, Mapper> map) {
        this.map = map;
    }

    public CharMapBuilder bind(char a, char b) {
        map.put(a, (ce) -> of(b));
        map.put(b, (ce) -> of(a));
        return this;
    }

    public CharMapBuilder bind(char a, Mapper m) {
        map.put(a, m);
        return this;
    }
}
