package com.joutvhu.dynamic.expression.analysis.match;

public final class MaybeMatcher<E, T extends Matcher<E>> extends BreakMatcher<E, T> {
    public MaybeMatcher(Matcher<E> parent) {
        super(parent);
    }

    MatchFunction<E> getFunction() {
        return null;
    }
}
