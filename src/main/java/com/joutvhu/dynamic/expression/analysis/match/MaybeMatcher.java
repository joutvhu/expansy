package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.Element;

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
        return new NamedMatcher<>(this, name);
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
    public MaybeMatcher<E, T> character(char... values) {
        children.character(values);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> characters(char... values) {
        children.characters(values);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> characters(char[] values, int time) {
        children.characters(values, time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> whitespace() {
        children.whitespace();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> whitespaces() {
        children.whitespaces();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> whitespaces(int time) {
        children.whitespaces(time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> digit() {
        children.digit();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> digits() {
        children.digits();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> digits(int time) {
        children.digits(time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> lowercase() {
        children.lowercase();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> lowercases() {
        children.lowercases();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> lowercases(int time) {
        children.lowercases(time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> uppercase() {
        children.uppercase();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> uppercases() {
        children.uppercases();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> uppercases(int time) {
        children.uppercases(time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> alphabet() {
        children.alphabet();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> alphabets() {
        children.alphabets();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> alphabets(int time) {
        children.alphabets(time);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> numeric() {
        children.numeric();
        return this;
    }

    @Override
    public MaybeMatcher<E, T> word() {
        children.word();
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
    public MaybeMatcher<E, T> analyzerIs(Element<E> element) {
        children.analyzerIs(element);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerIs(Element<E>... elements) {
        children.analyzerIs(elements);
        return this;
    }

    @Override
    public MaybeMatcher<E, T> analyzerIs(List<Element<E>> elements) {
        children.analyzerIs(elements);
        return this;
    }
}
