package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;

public final class LoopDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, MaybeDefiner<E, T>> {
    private T parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;

    public LoopDefiner(T parent) {
        this(parent, 0, null);
    }

    public LoopDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public LoopDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        this.minRepetitions = minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(LinearFilter filter) {

            }
        };
    }

    public T end() {
        return parent;
    }
}