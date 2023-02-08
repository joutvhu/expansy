package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public class DefaultMatcher<E> extends Matcher<E> {
    @Override
    public Matcher<E> equals(String value) {
        return this;
    }

    @Override
    public Matcher<E> maybe(String value) {
        return this;
    }

    @Override
    public Matcher<E> oneOf(List<String> values) {
        return this;
    }

    @Override
    public Matcher<E> repeat(String value, int time) {
        return this;
    }

    @Override
    public Matcher<E> repeat(String value, int minTime, int maxTime) {
        return this;
    }

    @Override
    public Matcher<E> match(String regex) {
        return this;
    }

    @Override
    public Matcher<E> match(String regex, int maxLength) {
        return this;
    }

    @Override
    public Matcher<E> match(Function<String, Boolean> checker) {
        return this;
    }

    @Override
    public Matcher<E> is(ElementAnalyzer<E> elementAnalyzer) {
        return this;
    }

    @Override
    public Matcher<E> are(List<ElementAnalyzer<E>> elementAnalyzers) {
        return this;
    }

    @Override
    public MatchFunctions<E> name(String name) {
        return this;
    }
}
