package com.joutvhu.dynamic.expression.analysis.match;

public interface Definer<E> extends Matches<E, Definer<E>> {
    Matches<E, ?> name(String name);
}
