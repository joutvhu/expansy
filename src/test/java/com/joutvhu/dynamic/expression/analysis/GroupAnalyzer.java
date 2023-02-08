package com.joutvhu.dynamic.expression.analysis;

public class GroupAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public E analysis(Matcher<E> matcher) {
        return (E) matcher
                .equals("(")
                // todo: match child elements
                .equals(")")
                .build(() -> {
                    return (E) null;
                });
    }
}
