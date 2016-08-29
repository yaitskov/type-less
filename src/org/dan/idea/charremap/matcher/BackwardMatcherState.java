package org.dan.idea.charremap.matcher;

import static org.dan.idea.charremap.matcher.MatcherState.coalesce;

public class BackwardMatcherState extends ForwardMatcherState {
    public BackwardMatcherState(MatcherState state) {
        super(state);
    }

    @Override
    public void next() {
        setNode(coalesce(getNode().getTreePrev()));
    }
}
