package com.joutvhu.dynamic.expression.analysis;

public class SumAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public E analysis(Matcher<E> matcher) {
        return (E) matcher
                // todo: match child elements
                .equals("+")
                // todo: match child elements
                .build(() -> {
                    return (E) null;
                });
    }
}
