package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class MatchFunctions<E> {
    public abstract Matcher<E> space();

    public abstract Matcher<E> spaces();

    public abstract Matcher<E> equals(String value);

    public abstract Matcher<E> maybe(String value);

    public final Matcher<E> oneOf(String... values) {
        return oneOf(Arrays.asList(values));
    }

    public abstract Matcher<E> oneOf(List<String> values);

    public abstract Matcher<E> repeat(String value, int time);

    public abstract Matcher<E> repeat(String value, int minTime, int maxTime);

    public abstract Matcher<E> match(String regex);

    public abstract Matcher<E> match(String regex, int maxLength);

    public abstract Matcher<E> match(Function<String, Boolean> checker);

    public abstract Matcher<E> is(ElementAnalyzer<E> elementAnalyzer);

    public final Matcher<E> is(ElementAnalyzer<E>... elementAnalyzers) {
        return is(Arrays.asList(elementAnalyzers));
    }

    public abstract Matcher<E> is(List<ElementAnalyzer<E>> elementAnalyzers);
}
