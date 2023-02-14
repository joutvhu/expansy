package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;

public abstract class MatchFunction<E> {
    protected String name;
    protected Matcher<E> parent;

    public MatchFunction(Matcher<E> parent) {
        this.parent = parent;
    }

    public MatchFunction(String name, Matcher<E> parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void match(LinearFilter filter);
}
