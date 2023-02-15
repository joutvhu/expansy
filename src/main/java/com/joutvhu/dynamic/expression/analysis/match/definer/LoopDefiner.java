package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;

public final class LoopDefiner<E, T extends Definer<E>> extends BreakDefiner<E, T> {
    private Integer minRepetitions;
    private Integer maxRepetitions;

    public LoopDefiner(T parent) {
        this(parent, 0, null);
    }

    public LoopDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public LoopDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        super(parent);
        this.minRepetitions = minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    Matcher<E> getMatcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(LinearFilter filter) {

            }
        };
    }
}
