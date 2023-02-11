package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public final class ProxyMatcher<E, T extends Matcher<E>> implements MatchFunctions<E, T> {
    protected String name;
    protected T parent;

    public ProxyMatcher(T parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public LoopMatcher<E, T> loop(int time) {
        return new LoopMatcher<>(parent, time);
    }

    @Override
    public LoopMatcher<E, T> loop(int minTime, Integer maxTime) {
        return new LoopMatcher<>(parent, minTime, maxTime);
    }

    @Override
    public T space() {
        parent.space();
        return parent;
    }

    @Override
    public T spaces() {
        parent.spaces();
        return parent;
    }

    @Override
    public T spaces(int time) {
        parent.spaces(time);
        return parent;
    }

    @Override
    public T equals(String value) {
        parent.equals(value);
        return parent;
    }

    @Override
    public T equals(String... values) {
        parent.equals(values);
        return parent;
    }

    @Override
    public T equals(List<String> values) {
        parent.equals(values);
        return parent;
    }

    @Override
    public T maybe(String value) {
        parent.maybe(value);
        return parent;
    }

    @Override
    public T repeat(String value, int time) {
        parent.repeat(value, time);
        return parent;
    }

    @Override
    public T repeat(String value, int minTime, int maxTime) {
        parent.repeat(value, minTime, maxTime);
        return parent;
    }

    @Override
    public T match(String regex) {
        parent.match(regex);
        return parent;
    }

    @Override
    public T match(String regex, int length) {
        parent.match(regex, length);
        return parent;
    }

    @Override
    public T match(Function<String, Boolean> checker) {
        parent.match(checker);
        return parent;
    }

    @Override
    public T analyzer(String analyzerName) {
        parent.analyzer(analyzerName);
        return parent;
    }

    @Override
    public T analyzer(String... analyzerNames) {
        parent.analyzer(analyzerNames);
        return parent;
    }

    @Override
    public T analyzer(List<String> analyzerNames) {
        parent.analyzer(analyzerNames);
        return parent;
    }

    @Override
    public T is(ElementAnalyzer<E> elementAnalyzer) {
        parent.is(elementAnalyzer);
        return parent;
    }

    @Override
    public T is(ElementAnalyzer<E>... elementAnalyzers) {
        parent.is(elementAnalyzers);
        return parent;
    }

    @Override
    public T is(List<ElementAnalyzer<E>> elementAnalyzers) {
        parent.is(elementAnalyzers);
        return parent;
    }
}
