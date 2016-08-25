package org.dan.idea.charremap;

import java.util.Optional;
import java.util.function.Function;

public interface Mapper extends Function<CharEvent, Optional<Character>> {
}
