package com.joutvhu.dynamic.expression.analysis.match;

public abstract class Matcher<E> extends NamedMatcher<E> {
    public abstract LoopMatcher<E> loop(int time);

    public abstract LoopMatcher<E> loop(int minTime, int maxTime);
}
