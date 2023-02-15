package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public final class LoopDefiner<E, T extends Definer<E>> extends BreakDefiner<E, T> {
    private Integer minTime;
    private Integer maxTime;

    public LoopDefiner(T parent, Integer time) {
        this(parent, time, time);
    }

    public LoopDefiner(T parent, Integer minTime, Integer maxTime) {
        super(parent);
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    Matcher<E> getMatcher() {
        return null;
    }
}
