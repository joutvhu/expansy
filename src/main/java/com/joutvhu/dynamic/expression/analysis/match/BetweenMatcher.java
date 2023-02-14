package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.Element;

import java.util.List;
import java.util.function.Function;

public class BetweenMatcher<E, T extends Matcher<E>> implements Matcher<E> {
    protected Matcher<E> parent;
    protected DefaultMatcher<E> children;

    public BetweenMatcher(Matcher<E> parent) {
        this.parent = parent;
        this.children = new DefaultMatcher<>();
    }

    MatchFunction<E> getFunction() {
        return null;
    }

    public IsMatcher<E, T> is() {
        return new IsMatcher<>(parent);
    }

    @Override
    public MatchFunctions<E, BetweenMatcher<E, T>> name(String name) {
        return new NamedMatcher<>(this, name);
    }

    @Override
    public MaybeMatcher<E, BetweenMatcher<E, T>> maybe() {
        return new MaybeMatcher<>(this);
    }

    @Override
    public LoopMatcher<E, BetweenMatcher<E, T>> loop(int time) {
        return new LoopMatcher<>(this, time);
    }

    @Override
    public LoopMatcher<E, BetweenMatcher<E, T>> loop(int minTime, Integer maxTime) {
        return new LoopMatcher<>(this, minTime, maxTime);
    }

    @Override
    public BetweenMatcher<E, BetweenMatcher<E, T>> between() {
        return new BetweenMatcher<>(this);
    }

    @Override
    public BetweenMatcher<E, T> space() {
        children.space();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> spaces() {
        children.spaces();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> spaces(int time) {
        children.spaces(time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> character(char... values) {
        children.character(values);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> characters(char... values) {
        children.characters(values);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> characters(char[] values, int time) {
        children.characters(values, time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> whitespace() {
        children.whitespace();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> whitespaces() {
        children.whitespaces();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> whitespaces(int time) {
        children.whitespaces(time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> digit() {
        children.digit();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> digits() {
        children.digits();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> digits(int time) {
        children.digits(time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> lowercase() {
        children.lowercase();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> lowercases() {
        children.lowercases();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> lowercases(int time) {
        children.lowercases(time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> uppercase() {
        children.uppercase();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> uppercases() {
        children.uppercases();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> uppercases(int time) {
        children.uppercases(time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> alphabet() {
        children.alphabet();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> alphabets() {
        children.alphabets();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> alphabets(int time) {
        children.alphabets(time);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> numeric() {
        children.numeric();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> word() {
        children.word();
        return this;
    }

    @Override
    public BetweenMatcher<E, T> equals(String value) {
        children.equals(value);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> equals(String... values) {
        children.equals(values);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> equals(List<String> values) {
        children.equals(values);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> match(String regex) {
        children.match(regex);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> match(String regex, int length) {
        children.match(regex, length);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> match(Function<String, Boolean> checker) {
        children.match(checker);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> analyzerName(String analyzerName) {
        children.analyzerName(analyzerName);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> analyzerName(String... analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> analyzerName(List<String> analyzerNames) {
        children.analyzerName(analyzerNames);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> analyzerIs(Element<E> element) {
        children.analyzerIs(element);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> analyzerIs(Element<E>... elements) {
        children.analyzerIs(elements);
        return this;
    }

    @Override
    public BetweenMatcher<E, T> analyzerIs(List<Element<E>> elements) {
        children.analyzerIs(elements);
        return this;
    }

    public class IsMatcher<E, T extends Matcher<E>> extends BreakMatcher<E, T> {
        public IsMatcher(Matcher<E> parent) {
            super(parent);
        }
    }
}
