package com.joutvhu.dynamic.expression.analysis.match;

public interface Matcher<E> extends MatchFunctions<E, Matcher<E>> {
    MatchFunctions<E, ?> name(String name);
}
