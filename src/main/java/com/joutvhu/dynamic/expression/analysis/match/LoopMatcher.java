package com.joutvhu.dynamic.expression.analysis.match;

public final class LoopMatcher<E> extends ProxyMatcher<E> {
    private DefaultMatcher<E> parent;
    private Integer minTime;
    private Integer maxTime;

    public LoopMatcher(DefaultMatcher<E> parent, Integer time) {
        this(parent, time, time);
    }

    public LoopMatcher(DefaultMatcher<E> parent, Integer minTime, Integer maxTime) {
        super(new DefaultMatcher<>());
        this.parent = parent;
        this.name = parent.takeName();
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public Matcher<E> end() {
        return this.parent;
    }
}
