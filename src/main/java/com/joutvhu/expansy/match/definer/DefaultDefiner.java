package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.matcher.CharacterMatcher;
import com.joutvhu.expansy.match.matcher.UnregisteredMatcher;
import com.joutvhu.expansy.match.matcher.EqualsMatcher;
import com.joutvhu.expansy.match.matcher.FunctionMatcher;
import com.joutvhu.expansy.match.matcher.NumericMatcher;
import com.joutvhu.expansy.match.matcher.RegexMatcher;
import com.joutvhu.expansy.match.matcher.RegisteredMatcher;
import com.joutvhu.expansy.match.matcher.WordMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DefaultDefiner<E> implements Definer<E> {
    List<Matcher<E>> matchers = new ArrayList<>();

    @Override
    public Matches<E, DefaultDefiner<E>> name(String name) {
        return new NamedDefiner<>(this, name);
    }

    @Override
    public Matcher<E> matcher() {
        int size = matchers.size();
        return size > 0 ? matchers.get(size - 1) : null;
    }

    @Override
    public MaybeDefiner<E, DefaultDefiner<E>> maybe() {
        MaybeDefiner<E, DefaultDefiner<E>> matcher = new MaybeDefiner<>(this);
        matchers.add(matcher.matcher());
        return matcher;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop() {
        LoopDefiner<E, DefaultDefiner<E>> matcher = new LoopDefiner<>(this);
        matchers.add(matcher.matcher());
        return matcher;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop(int repetitions) {
        LoopDefiner<E, DefaultDefiner<E>> matcher = new LoopDefiner<>(this, repetitions);
        matchers.add(matcher.matcher());
        return matcher;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop(int minRepetitions, Integer maxRepetitions) {
        LoopDefiner<E, DefaultDefiner<E>> matcher = new LoopDefiner<>(this, minRepetitions, maxRepetitions);
        matchers.add(matcher.matcher());
        return matcher;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between() {
        BetweenDefiner<E, DefaultDefiner<E>> matcher = new BetweenDefiner<>(this);
        matchers.add(matcher.matcher());
        return matcher;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between(int repetitions) {
        BetweenDefiner<E, DefaultDefiner<E>> matcher = new BetweenDefiner<>(this, repetitions);
        matchers.add(matcher.matcher());
        return matcher;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between(int minRepetitions, Integer maxRepetitions) {
        BetweenDefiner<E, DefaultDefiner<E>> matcher = new BetweenDefiner<>(this, minRepetitions, maxRepetitions);
        matchers.add(matcher.matcher());
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
    public DefaultDefiner<E> characters(char[] values, int repetitions) {
        matchers.add(new CharacterMatcher<>(this, values, repetitions));
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
    public DefaultDefiner<E> whitespaces(int repetitions) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, repetitions));
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
    public DefaultDefiner<E> digits(int repetitions) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, repetitions));
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
    public DefaultDefiner<E> lowercases(int repetitions) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, repetitions));
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
    public DefaultDefiner<E> uppercases(int repetitions) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, repetitions));
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
    public DefaultDefiner<E> alphabets(int repetitions) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, repetitions));
        return this;
    }

    @Override
    public DefaultDefiner<E> numeric() {
        matchers.add(new NumericMatcher<>(this));
        return this;
    }

    @Override
    public DefaultDefiner<E> word() {
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
    public DefaultDefiner<E> equalsIgnoreCase(String value) {
        matchers.add(new EqualsMatcher<>(this, value, true));
        return this;
    }

    @Override
    public DefaultDefiner<E> equalsIgnoreCase(String... values) {
        return equalsIgnoreCase(Arrays.asList(values));
    }

    @Override
    public DefaultDefiner<E> equalsIgnoreCase(List<String> values) {
        matchers.add(new EqualsMatcher<>(this, values, true));
        return this;
    }

    @Override
    public DefaultDefiner<E> pattern(String regex) {
        matchers.add(new RegexMatcher<>(this, regex));
        return this;
    }

    @Override
    public DefaultDefiner<E> pattern(String regex, int length) {
        matchers.add(new RegexMatcher<>(this, regex, 0, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> pattern(String regex, int minLength, int maxLength) {
        matchers.add(new RegexMatcher<>(this, regex, 0, minLength, maxLength));
        return this;
    }

    @Override
    public DefaultDefiner<E> check(Function<String, Boolean> checker) {
        matchers.add(new FunctionMatcher<>(this, checker));
        return this;
    }

    @Override
    public DefaultDefiner<E> element(String element) {
        matchers.add(new RegisteredMatcher<>(this, element));
        return this;
    }

    public DefaultDefiner<E> element(List<String> elements) {
        matchers.add(new RegisteredMatcher<>(this, elements));
        return this;
    }

    @Override
    public DefaultDefiner<E> element(String... elements) {
        return element(Arrays.asList(elements));
    }

    @Override
    public DefaultDefiner<E> element(Element<E> element) {
        matchers.add(new UnregisteredMatcher<>(this, element));
        return this;
    }

    @Override
    public DefaultDefiner<E> element(Element<E>... elements) {
        matchers.add(new UnregisteredMatcher<>(this, Arrays.asList(elements)));
        return this;
    }

    @Override
    public DefaultDefiner<E> elements() {
        matchers.add(new RegisteredMatcher<>(this));
        return this;
    }
}
