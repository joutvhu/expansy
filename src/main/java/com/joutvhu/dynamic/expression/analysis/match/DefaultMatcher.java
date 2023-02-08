package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.function.EqualsMatcher;
import com.joutvhu.dynamic.expression.analysis.match.function.RegexMatcher;

import java.util.List;
import java.util.function.Function;

public class DefaultMatcher<E> extends Matcher<E> {
    private String name;

    public String takeName() {
        String result = this.name;
        this.name = null;
        return result;
    }

    @Override
    public MatchFunctions<E> name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Matcher<E> space() {
        return null;
    }

    @Override
    public Matcher<E> spaces() {
        return null;
    }

    @Override
    public Matcher<E> equals(String value) {
        return new EqualsMatcher<>(this, value);
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
        return new RegexMatcher<>(this, regex);
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
    public Matcher<E> is(List<ElementAnalyzer<E>> elementAnalyzers) {
        return null;
    }
}
