package org.dan.idea.charremap;

import java.util.Map;

public class CharMapBuilder {
    private final Map<Character, Character> map;

    public CharMapBuilder(Map<Character, Character> map) {
        this.map = map;
    }

    public CharMapBuilder add(char a, char b) {
        map.put(a, b);
        map.put(b, a);
        return this;
    }
}
