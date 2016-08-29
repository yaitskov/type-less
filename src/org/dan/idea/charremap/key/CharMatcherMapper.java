package org.dan.idea.charremap.key;

import static java.util.Optional.empty;

import java.util.Optional;

import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class CharMatcherMapper extends MatcherMapper {
    private final char out;
    private final Matcher matcher;

    public CharMatcherMapper(char out, Matcher matcher) {
        this.out = out;
        this.matcher = matcher;
    }

    @Override
    protected Optional<Character> map(MatcherState state) {
        if (matcher.test(state)) {
            return Optional.of(out);
        }
        return empty();
    }
}
