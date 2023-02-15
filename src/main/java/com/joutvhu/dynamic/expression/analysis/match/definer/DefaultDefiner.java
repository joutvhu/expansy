package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunctions;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.AnalyzerMatcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.CharacterMatcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.EqualsMatcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.FunctionMatcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.NumericMatcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.RegexMatcher;
import com.joutvhu.dynamic.expression.analysis.match.matcher.WordMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DefaultDefiner<E> implements Definer<E> {
    List<Matcher<E>> matchers = new ArrayList<>();

    @Override
    public MatchFunctions<E, DefaultDefiner<E>> name(String name) {
        return new NamedDefiner<>(this, name);
    }

    @Override
    public MaybeDefiner<E, DefaultDefiner<E>> maybe() {
        MaybeDefiner<E, DefaultDefiner<E>> matcher = new MaybeDefiner<>(this);
        matchers.add(matcher.getMatcher());
        return matcher;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop(int time) {
        LoopDefiner<E, DefaultDefiner<E>> matcher = new LoopDefiner<>(this, time);
        matchers.add(matcher.getMatcher());
        return matcher;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop(int minTime, Integer maxTime) {
        LoopDefiner<E, DefaultDefiner<E>> matcher = new LoopDefiner<>(this, minTime, maxTime);
        matchers.add(matcher.getMatcher());
        return matcher;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between() {
        BetweenDefiner<E, DefaultDefiner<E>> matcher = new BetweenDefiner<>(this);
        matchers.add(matcher.getMatcher());
        return matcher;
    }

    @Override
    public DefaultDefiner<E> space() {
        matchers.add(new CharacterMatcher<>(this, ' ', 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> spaces() {
        matchers.add(new CharacterMatcher<>(this, ' ', null));
        return this;
    }

    @Override
    public DefaultDefiner<E> spaces(int time) {
        matchers.add(new CharacterMatcher<>(this, ' ', time));
        return this;
    }

    @Override
    public DefaultDefiner<E> character(char... values) {
        matchers.add(new CharacterMatcher<>(this, values, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> characters(char... values) {
        matchers.add(new CharacterMatcher<>(this, values, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> characters(char[] values, int time) {
        matchers.add(new CharacterMatcher<>(this, values, time));
        return this;
    }

    @Override
    public DefaultDefiner<E> whitespace() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> whitespaces() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> whitespaces(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, time));
        return this;
    }

    @Override
    public DefaultDefiner<E> digit() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> digits() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> digits(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, time));
        return this;
    }

    @Override
    public DefaultDefiner<E> lowercase() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> lowercases() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> lowercases(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, time));
        return this;
    }

    @Override
    public DefaultDefiner<E> uppercase() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> uppercases() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> uppercases(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, time));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphabet() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphabets() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphabets(int time) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, time));
        return this;
    }

    @Override
    public Definer<E> numeric() {
        matchers.add(new NumericMatcher<>(this));
        return this;
    }

    @Override
    public Definer<E> word() {
        matchers.add(new WordMatcher<>(this));
        return this;
    }

    @Override
    public DefaultDefiner<E> equals(String value) {
        matchers.add(new EqualsMatcher<>(this, value));
        return this;
    }

    @Override
    public DefaultDefiner<E> equals(String... values) {
        return equals(Arrays.asList(values));
    }

    @Override
    public DefaultDefiner<E> equals(List<String> values) {
        matchers.add(new EqualsMatcher<>(this, values));
        return this;
    }

    @Override
    public DefaultDefiner<E> match(String regex) {
        matchers.add(new RegexMatcher<>(this, regex, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> match(String regex, int length) {
        matchers.add(new RegexMatcher<>(this, regex, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> match(Function<String, Boolean> checker) {
        matchers.add(new FunctionMatcher<>(this, checker));
        return this;
    }

    @Override
    public DefaultDefiner<E> elementName(String elementName) {
        return this;
    }

    @Override
    public DefaultDefiner<E> elementName(List<String> elementNames) {
        return this;
    }

    @Override
    public DefaultDefiner<E> elementName(String... elementNames) {
        return elementName(Arrays.asList(elementNames));
    }

    @Override
    public DefaultDefiner<E> element(Element<E> element) {
        matchers.add(new AnalyzerMatcher<>(this, element));
        return this;
    }

    @Override
    public DefaultDefiner<E> element(Element<E>... elements) {
        return element(Arrays.asList(elements));
    }

    @Override
    public DefaultDefiner<E> element(List<Element<E>> elements) {
        matchers.add(new AnalyzerMatcher<>(this, elements));
        return this;
    }
}
