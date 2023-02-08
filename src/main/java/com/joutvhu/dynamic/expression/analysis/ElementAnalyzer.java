package com.joutvhu.dynamic.expression.analysis;

public abstract class ElementAnalyzer<E> {
    public abstract String getName();

    public int getType() {
        return 0;
    }

    public abstract E analysis(Matcher<E> matcher);
}
