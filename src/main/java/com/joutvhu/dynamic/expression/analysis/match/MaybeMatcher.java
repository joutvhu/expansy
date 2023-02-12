package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public final class MaybeMatcher<E, T extends Matcher<E>> extends MatchFunction<E> implements Matcher<E> {
    private DefaultMatcher<E> children;

    public MaybeMatcher(Matcher<E> parent) {
        super(parent);
        this.children = new DefaultMatcher<>();
    }

    public T end() {
        return (T) parent;
    }

    @Override
    public MatchFunctions<E, MaybeMatcher<E, T>> name(String name) {
        return new ProxyMatcher<>(this, name);
    }

    @Override
    public MaybeMatcher<E, MaybeMatcher<E, T>> maybe() {
        return new MaybeMatcher<>(this);
    }

    @Override
    public LoopMatcher<E, MaybeMatcher<E, T>> loop(int time) {
        return new LoopMatcher<>(this, time);
    }

    @Override
    public LoopMatcher<E, MaybeMatcher<E, T>> loop(int minTime, Integer maxTime) {
        return new LoopMatcher<>(this, minTime, maxTime);
    }

    @Override
    public MaybeMatcher<E, T> space() {
        children.space();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> spaces() {
        children.spaces();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> spaces(int time) {
        children.spaces(time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> equals(String value) {
        children.equals(value);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> equals(String... values) {
        children.equals(values);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> equals(List<String> values) {
        children.equals(values);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> match(String regex) {
        children.match(regex);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> match(String regex, int length) {
        children.match(regex, length);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> match(Function<String, Boolean> checker) {
        children.match(checker);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerName(String analyzerName) {
        children.analyzerName(analyzerName);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerName(String... analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerName(List<String> analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerIs(ElementAnalyzer<E> elementAnalyzer) {
        children.analyzerIs(elementAnalyzer);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerIs(ElementAnalyzer<E>... elementAnalyzers) {
        children.analyzerIs(elementAnalyzers);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerIs(List<ElementAnalyzer<E>> elementAnalyzers) {
        children.analyzerIs(elementAnalyzers);
        return this;
    }
}
