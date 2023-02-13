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
    public NamedMatcher<E, LoopMatcher<E, T>> name(String name) {
        return new NamedMatcher<>(this, name);
    }

    @Override
    public MaybeMatcher<E, LoopMatcher<E, T>> maybe() {
        return new MaybeMatcher<>(this);
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
    public LoopMatcher<E, T> character(char... values) {
        children.character(values);
        return this;
    }

    @Override
    public LoopMatcher<E, T> characters(char... values) {
        children.characters(values);
        return this;
    }

    @Override
    public LoopMatcher<E, T> characters(char[] values, int time) {
        children.characters(values, time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> whitespace() {
        children.whitespace();
        return this;
    }

    @Override
    public LoopMatcher<E, T> whitespaces() {
        children.whitespaces();
        return this;
    }

    @Override
    public LoopMatcher<E, T> whitespaces(int time) {
        children.whitespaces(time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> digit() {
        children.digit();
        return this;
    }

    @Override
    public LoopMatcher<E, T> digits() {
        children.digits();
        return this;
    }

    @Override
    public LoopMatcher<E, T> digits(int time) {
        children.digits(time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> lowercase() {
        children.lowercase();
        return this;
    }

    @Override
    public LoopMatcher<E, T> lowercases() {
        children.lowercases();
        return this;
    }

    @Override
    public LoopMatcher<E, T> lowercases(int time) {
        children.lowercases(time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> uppercase() {
        children.uppercase();
        return this;
    }

    @Override
    public LoopMatcher<E, T> uppercases() {
        children.uppercases();
        return this;
    }

    @Override
    public LoopMatcher<E, T> uppercases(int time) {
        children.uppercases(time);
        return this;
    }

    @Override
    public LoopMatcher<E, T> alphabet() {
        children.alphabet();
        return this;
    }

    @Override
    public LoopMatcher<E, T> alphabets() {
        children.alphabets();
        return this;
    }

    @Override
    public LoopMatcher<E, T> alphabets(int time) {
        children.alphabets(time);
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
    public LoopMatcher<E, T> analyzerName(String analyzerName) {
        children.analyzerName(analyzerName);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzerName(String... analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzerName(List<String> analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzerIs(ElementAnalyzer<E> elementAnalyzer) {
        children.analyzerIs(elementAnalyzer);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzerIs(ElementAnalyzer<E>... elementAnalyzers) {
        children.analyzerIs(elementAnalyzers);
        return this;
    }

    @Override
    public LoopMatcher<E, T> analyzerIs(List<ElementAnalyzer<E>> elementAnalyzers) {
        children.analyzerIs(elementAnalyzers);
        return this;
    }
}
