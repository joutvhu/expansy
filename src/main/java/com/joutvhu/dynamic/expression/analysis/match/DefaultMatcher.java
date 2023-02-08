package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.function.EqualsMatcher;
import com.joutvhu.dynamic.expression.analysis.match.function.FunctionMatcher;
import com.joutvhu.dynamic.expression.analysis.match.function.RegexMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DefaultMatcher<E> extends Matcher<E> {
    private String name;
    private List<Matcher<E>> matchers = new ArrayList<>();

    @Override
    public MatchFunctions<E> name(String name) {
        this.name = name;
        return this;
    }

    String takeName() {
        String result = this.name;
        this.name = null;
        return result;
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
        return add(new EqualsMatcher<>(this, value));
    }

    @Override
    public Matcher<E> maybe(String value) {
        return this;
    }

    @Override
    public Matcher<E> oneOf(List<String> values) {
        return add(new EqualsMatcher<>(this, values));
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
        return add(new RegexMatcher<>(this, regex, null));
    }

    @Override
    public Matcher<E> match(String regex, int length) {
        return add(new RegexMatcher<>(this, regex, length));
    }

    @Override
    public Matcher<E> match(Function<String, Boolean> checker) {
        return add(new FunctionMatcher<>(this, checker));
    }

    @Override
    public Matcher<E> is(ElementAnalyzer<E> elementAnalyzer) {
        return this;
    }

    @Override
    public Matcher<E> is(List<ElementAnalyzer<E>> elementAnalyzers) {
        return null;
    }

    private Matcher<E> add(Matcher<E> matcher) {
        this.matchers.add(matcher);
        return matcher;
    }
}
