package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public abstract class ProxyMatcher<E> extends Matcher<E> {
    protected String name;
    protected Matcher<E> parent;

    public ProxyMatcher(DefaultMatcher<E> parent) {
        this.parent = parent;
        this.name = parent.takeName();
    }

    @Override
    public MatchFunctions<E> name(String name) {
        return parent.name(name);
    }

    @Override
    public Matcher<E> space() {
        return parent.space();
    }

    @Override
    public Matcher<E> spaces() {
        return parent.spaces();
    }

    @Override
    public Matcher<E> spaces(int time) {
        return parent.spaces(time);
    }

    @Override
    public Matcher<E> equals(String value) {
        return parent.equals(value);
    }

    @Override
    public Matcher<E> equals(List<String> values) {
        return parent.equals(values);
    }

    @Override
    public Matcher<E> maybe(String value) {
        return parent.maybe(value);
    }

    @Override
    public Matcher<E> repeat(String value, int time) {
        return parent.repeat(value, time);
    }

    @Override
    public Matcher<E> repeat(String value, int minTime, int maxTime) {
        return parent.repeat(value, minTime, maxTime);
    }

    @Override
    public Matcher<E> match(String regex) {
        return parent.match(regex);
    }

    @Override
    public Matcher<E> match(String regex, int length) {
        return parent.match(regex, length);
    }

    @Override
    public Matcher<E> match(Function<String, Boolean> checker) {
        return parent.match(checker);
    }

    @Override
    public Matcher<E> is(ElementAnalyzer<E> elementAnalyzer) {
        return parent.is(elementAnalyzer);
    }

    @Override
    public Matcher<E> is(List<ElementAnalyzer<E>> elementAnalyzers) {
        return parent.is(elementAnalyzers);
    }
}
