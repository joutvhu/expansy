package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.func.AnalyzerMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.CharacterMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.EqualsMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.FunctionMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.NumericMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.RegexMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.WordMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DefaultMatcher<E> implements Matcher<E> {
    List<MatchFunction<E>> matchers = new ArrayList<>();

    @Override
    public MatchFunctions<E, DefaultMatcher<E>> name(String name) {
        return new NamedMatcher<>(this, name);
    }

    @Override
    public MaybeMatcher<E, DefaultMatcher<E>> maybe() {
        MaybeMatcher<E, DefaultMatcher<E>> matcher = new MaybeMatcher<>(this);
        matchers.add(matcher.getFunction());
        return matcher;
    }

    @Override
    public LoopMatcher<E, DefaultMatcher<E>> loop(int time) {
        LoopMatcher<E, DefaultMatcher<E>> matcher = new LoopMatcher<>(this, time);
        matchers.add(matcher.getFunction());
        return matcher;
    }

    @Override
    public LoopMatcher<E, DefaultMatcher<E>> loop(int minTime, Integer maxTime) {
        LoopMatcher<E, DefaultMatcher<E>> matcher = new LoopMatcher<>(this, minTime, maxTime);
        matchers.add(matcher.getFunction());
        return matcher;
    }

    @Override
    public BetweenMatcher<E, DefaultMatcher<E>> between() {
        BetweenMatcher<E, DefaultMatcher<E>> matcher = new BetweenMatcher<>(this);
        matchers.add(matcher.getFunction());
        return matcher;
    }

    @Override
    public DefaultMatcher<E> space() {
        matchers.add(new CharacterMatcher<>(this, ' ', 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> spaces() {
        matchers.add(new CharacterMatcher<>(this, ' ', null));
        return this;
    }

    @Override
    public DefaultMatcher<E> spaces(int time) {
        matchers.add(new CharacterMatcher<>(this, ' ', time));
        return this;
    }

    @Override
    public DefaultMatcher<E> character(char... values) {
        matchers.add(new CharacterMatcher<>(this, values, 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> characters(char... values) {
        matchers.add(new CharacterMatcher<>(this, values, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> characters(char[] values, int time) {
        matchers.add(new CharacterMatcher<>(this, values, time));
        return this;
    }

    @Override
    public DefaultMatcher<E> whitespace() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> whitespaces() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> whitespaces(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, time));
        return this;
    }

    @Override
    public DefaultMatcher<E> digit() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> digits() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> digits(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, time));
        return this;
    }

    @Override
    public DefaultMatcher<E> lowercase() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> lowercases() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> lowercases(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, time));
        return this;
    }

    @Override
    public DefaultMatcher<E> uppercase() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> uppercases() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> uppercases(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, time));
        return this;
    }

    @Override
    public DefaultMatcher<E> alphabet() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, 1));
        return this;
    }

    @Override
    public DefaultMatcher<E> alphabets() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> alphabets(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, time));
        return this;
    }

    @Override
    public Matcher<E> numeric() {
        matchers.add(new NumericMatcher<>(this));
        return this;
    }

    @Override
    public Matcher<E> word() {
        matchers.add(new WordMatcher<>(this));
        return this;
    }

    @Override
    public DefaultMatcher<E> equals(String value) {
        matchers.add(new EqualsMatcher<>(this, value));
        return this;
    }

    @Override
    public DefaultMatcher<E> equals(String... values) {
        return equals(Arrays.asList(values));
    }

    @Override
    public DefaultMatcher<E> equals(List<String> values) {
        matchers.add(new EqualsMatcher<>(this, values));
        return this;
    }

    @Override
    public DefaultMatcher<E> match(String regex) {
        matchers.add(new RegexMatcher<>(this, regex, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> match(String regex, int length) {
        matchers.add(new RegexMatcher<>(this, regex, length));
        return this;
    }

    @Override
    public DefaultMatcher<E> match(Function<String, Boolean> checker) {
        matchers.add(new FunctionMatcher<>(this, checker));
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerName(String analyzerName) {
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerName(List<String> analyzerNames) {
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerName(String... analyzerNames) {
        return analyzerName(Arrays.asList(analyzerNames));
    }

    @Override
    public DefaultMatcher<E> analyzerIs(Element<E> element) {
        matchers.add(new AnalyzerMatcher<>(this, element));
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerIs(Element<E>... elements) {
        return analyzerIs(Arrays.asList(elements));
    }

    @Override
    public DefaultMatcher<E> analyzerIs(List<Element<E>> elements) {
        matchers.add(new AnalyzerMatcher<>(this, elements));
        return this;
    }
}
