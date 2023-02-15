package com.joutvhu.dynamic.expression.analysis.match;

public interface Definer<E> extends MatchFunctions<E, Definer<E>> {
    MatchFunctions<E, ?> name(String name);
}
