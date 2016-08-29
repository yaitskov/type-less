package org.dan.idea.charremap.composite;

import com.intellij.lang.ASTNode;
import org.dan.idea.charremap.matcher.Matcher;
import org.dan.idea.charremap.matcher.MatcherState;

public class Or implements Matcher {
    private final Matcher[] options;

    public Or(Matcher... options) {
        this.options = options;
    }

    public static Or or(Matcher... options) {
        return new Or(options);
    }

    @Override
    public boolean test(MatcherState s) {
        ASTNode origin = s.getNode();
        for (Matcher m : options) {
            if (m.test(s)) {
                return true;
            }
            s.setNode(origin);
        }
        return false;
    }
}
