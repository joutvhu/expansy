package com.joutvhu.dynamic.expression.analysis;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class Matcher<E> {
    public abstract Matcher<E> equals(String value);

    public Matcher<E> oneOf(String... values) {
        return oneOf(Arrays.asList(values));
    }

    public abstract Matcher<E> oneOf(List<String> values);

    public abstract Matcher<E> repeat(String value, int time);

    public abstract Matcher<E> match(String regex);

    public abstract Matcher<E> match(Function<String, Boolean> checker);

    public abstract Matcher<E> withType(int type);

    public abstract Matcher<E> is(ElementAnalyzer<E> elementAnalyzer);

    public Matcher<E> is(ElementAnalyzer<E>... elementAnalyzers) {
        return is(Arrays.asList(elementAnalyzers));
    }

    public abstract Matcher<E> is(List<ElementAnalyzer<E>> elementAnalyzers);

    public final E build(Builder<E> builder) {
        return builder.build();
    }
}
