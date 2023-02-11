package com.joutvhu.dynamic.expression.analysis.match;

public abstract class NamedMatcher<E> extends MatchFunctions<E> {
    public abstract MatchFunctions<E> name(String name);
}
