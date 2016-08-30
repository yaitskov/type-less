package org.dan.idea.charremap;

import java.util.HashMap;
import java.util.Map;

import org.dan.idea.charremap.key.CharMatcherMapper;
import org.dan.idea.charremap.matcher.Matchers;

public class Mappers {
    public static final Map<Character, Mapper> CHAR_MAP = new CharMapBuilder(new HashMap<>())
            .bind('2', new CharMatcherMapper('@', Matchers.AT_MATCHER))
            .bind(',', new CharMatcherMapper('<', Matchers.LESS_MATCHER))
            .bind('[', '{')
            .bind(']', '}')
            .bind('0', ')')
            .bind('-', '_')
            .bind('9', '(')
            .bind('`', '~')
            .bind('\\', '|')
            .bind('\'', '"')
            .getMap();
}
