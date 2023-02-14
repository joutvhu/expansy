package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.Element;

import java.util.List;
import java.util.function.Function;

public abstract class BreakMatcher<E, T extends Matcher<E>> implements Matcher<E> {
    protected Matcher<E> parent;
    protected DefaultMatcher<E> children;

    public BreakMatcher(Matcher<E> parent) {
        this.parent = parent;
        this.children = new DefaultMatcher<>();
    }

    public T end() {
        return (T) parent;
    }

    @Override
    public MatchFunctions<E, BreakMatcher<E, T>> name(String name) {
        return new NamedMatcher<>(this, name);
    }

    @Override
    public MaybeMatcher<E, BreakMatcher<E, T>> maybe() {
        return new MaybeMatcher<>(this);
    }

    @Override
    public LoopMatcher<E, BreakMatcher<E, T>> loop(int time) {
        return new LoopMatcher<>(this, time);
    }

    @Override
    public LoopMatcher<E, BreakMatcher<E, T>> loop(int minTime, Integer maxTime) {
        return new LoopMatcher<>(this, minTime, maxTime);
    }

    @Override
    public BetweenMatcher<E, BreakMatcher<E, T>> between() {
        return new BetweenMatcher<>(this);
    }

    @Override
    public BreakMatcher<E, T> space() {
        children.space();
        return this;
    }

    @Override
    public BreakMatcher<E, T> spaces() {
        children.spaces();
        return this;
    }

    @Override
    public BreakMatcher<E, T> spaces(int time) {
        children.spaces(time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> character(char... values) {
        children.character(values);
        return this;
    }

    @Override
    public BreakMatcher<E, T> characters(char... values) {
        children.characters(values);
        return this;
    }

    @Override
    public BreakMatcher<E, T> characters(char[] values, int time) {
        children.characters(values, time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> whitespace() {
        children.whitespace();
        return this;
    }

    @Override
    public BreakMatcher<E, T> whitespaces() {
        children.whitespaces();
        return this;
    }

    @Override
    public BreakMatcher<E, T> whitespaces(int time) {
        children.whitespaces(time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> digit() {
        children.digit();
        return this;
    }

    @Override
    public BreakMatcher<E, T> digits() {
        children.digits();
        return this;
    }

    @Override
    public BreakMatcher<E, T> digits(int time) {
        children.digits(time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> lowercase() {
        children.lowercase();
        return this;
    }

    @Override
    public BreakMatcher<E, T> lowercases() {
        children.lowercases();
        return this;
    }

    @Override
    public BreakMatcher<E, T> lowercases(int time) {
        children.lowercases(time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> uppercase() {
        children.uppercase();
        return this;
    }

    @Override
    public BreakMatcher<E, T> uppercases() {
        children.uppercases();
        return this;
    }

    @Override
    public BreakMatcher<E, T> uppercases(int time) {
        children.uppercases(time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> alphabet() {
        children.alphabet();
        return this;
    }

    @Override
    public BreakMatcher<E, T> alphabets() {
        children.alphabets();
        return this;
    }

    @Override
    public BreakMatcher<E, T> alphabets(int time) {
        children.alphabets(time);
        return this;
    }

    @Override
    public BreakMatcher<E, T> numeric() {
        children.numeric();
        return this;
    }

    @Override
    public BreakMatcher<E, T> word() {
        children.word();
        return this;
    }

    @Override
    public BreakMatcher<E, T> equals(String value) {
        children.equals(value);
        return this;
    }

    @Override
    public BreakMatcher<E, T> equals(String... values) {
        children.equals(values);
        return this;
    }

    @Override
    public BreakMatcher<E, T> equals(List<String> values) {
        children.equals(values);
        return this;
    }

    @Override
    public BreakMatcher<E, T> match(String regex) {
        children.match(regex);
        return this;
    }

    @Override
    public BreakMatcher<E, T> match(String regex, int length) {
        children.match(regex, length);
        return this;
    }

    @Override
    public BreakMatcher<E, T> match(Function<String, Boolean> checker) {
        children.match(checker);
        return this;
    }

    @Override
    public BreakMatcher<E, T> analyzerName(String analyzerName) {
        children.analyzerName(analyzerName);
        return this;
    }

    @Override
    public BreakMatcher<E, T> analyzerName(String... analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public BreakMatcher<E, T> analyzerName(List<String> analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public BreakMatcher<E, T> analyzerIs(Element<E> element) {
        children.analyzerIs(element);
        return this;
    }

    @Override
    public BreakMatcher<E, T> analyzerIs(Element<E>... elements) {
        children.analyzerIs(elements);
        return this;
    }

    @Override
    public BreakMatcher<E, T> analyzerIs(List<Element<E>> elements) {
        children.analyzerIs(elements);
        return this;
    }
}
