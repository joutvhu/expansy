package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public final class LoopMatcher<E, T extends Matcher<E>> extends MatchFunction<E> implements Matcher<E> {
    private DefaultMatcher<E> children;
    private Integer minTime;
    private Integer maxTime;

    public LoopMatcher(T parent, Integer time) {
        this(parent, time, time);
    }

    public LoopMatcher(T parent, Integer minTime, Integer maxTime) {
        super(parent);
        this.children = new DefaultMatcher<>();
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public T end() {
        return (T) parent;
    }

    @Override
    public ProxyMatcher<E, LoopMatcher<E, T>> name(String name) {
        return new ProxyMatcher<>(this, name);
    }

    @Override
    public LoopMatcher<E, LoopMatcher<E, T>> loop(int time) {
        return new LoopMatcher<>(this, time);
    }

    @Override
    public LoopMatcher<E, LoopMatcher<E, T>> loop(int minTime, Integer maxTime) {
        return new LoopMatcher<>(this, minTime, maxTime);
    }

    @Override
    public LoopMatcher<E, T> space() {
        children.space();
        return this;
    }

    @Override
    public LoopMatcher<E, T> spaces() {
        children.spaces();
        return this;
    }

    @Override
    public LoopMatcher<E, T> spaces(int time) {
        children.spaces(time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> equals(String value) {
        children.equals(value);
        return this;
    }

    @Override
    public LoopMatcher<E, T> equals(String... values) {
        children.equals(values);
        return this;
    }

    @Override
    public LoopMatcher<E, T> equals(List<String> values) {
        children.equals(values);
        return this;
    }

    @Override
    public LoopMatcher<E, T> maybe(String value) {
        children.maybe(value);
        return this;
    }

    @Override
    public LoopMatcher<E, T> repeat(String value, int time) {
        children.repeat(value, time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> repeat(String value, int minTime, int maxTime) {
        children.repeat(value, minTime, maxTime);
        return this;
    }

    @Override
    public LoopMatcher<E, T> match(String regex) {
        children.match(regex);
        return this;
    }

    @Override
    public LoopMatcher<E, T> match(String regex, int length) {
        children.match(regex, length);
        return this;
    }

    @Override
    public LoopMatcher<E, T> match(Function<String, Boolean> checker) {
        children.match(checker);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzer(String analyzerName) {
        children.analyzer(analyzerName);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzer(String... analyzerNames) {
        children.analyzer(analyzerNames);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzer(List<String> analyzerNames) {
        children.analyzer(analyzerNames);
        return this;
    }

    @Override
    public LoopMatcher<E, T> is(ElementAnalyzer<E> elementAnalyzer) {
        children.is(elementAnalyzer);
        return this;
    }

    @Override
    public LoopMatcher<E, T> is(ElementAnalyzer<E>... elementAnalyzers) {
        children.is(elementAnalyzers);
        return this;
    }

    @Override
    public LoopMatcher<E, T> is(List<ElementAnalyzer<E>> elementAnalyzers) {
        children.is(elementAnalyzers);
        return this;
    }
}
