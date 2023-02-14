package com.joutvhu.dynamic.expression.analysis.match;

public final class LoopMatcher<E, T extends Matcher<E>> extends BreakMatcher<E, T> {
    private Integer minTime;
    private Integer maxTime;

    public LoopMatcher(T parent, Integer time) {
        this(parent, time, time);
    }

    public LoopMatcher(T parent, Integer minTime, Integer maxTime) {
        super(parent);
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    MatchFunction<E> getFunction() {
        return null;
    }
}
