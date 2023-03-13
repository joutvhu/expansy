package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.matcher.BoolFunctionMatcher;
import com.joutvhu.expansy.match.matcher.CharacterMatcher;
import com.joutvhu.expansy.match.matcher.EqualsMatcher;
import com.joutvhu.expansy.match.matcher.ExcludeMatcher;
import com.joutvhu.expansy.match.matcher.MatchFunctionMatcher;
import com.joutvhu.expansy.match.matcher.NumericMatcher;
import com.joutvhu.expansy.match.matcher.QuoteMatcher;
import com.joutvhu.expansy.match.matcher.RegexMatcher;
import com.joutvhu.expansy.match.matcher.RegisteredMatcher;
import com.joutvhu.expansy.match.matcher.SizeMatcher;
import com.joutvhu.expansy.match.matcher.UnregisteredMatcher;
import com.joutvhu.expansy.match.matcher.WordMatcher;
import com.joutvhu.expansy.match.type.MatchFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

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
        MaybeDefiner<E, DefaultDefiner<E>> definer = new MaybeDefiner<>(this);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public OrDefiner<E, ?> options() {
        OrDefiner<E, DefaultDefiner<E>> definer = new OrDefiner<>(this);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop() {
        LoopDefiner<E, DefaultDefiner<E>> definer = new LoopDefiner<>(this);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop(int repetitions) {
        LoopDefiner<E, DefaultDefiner<E>> definer = new LoopDefiner<>(this, repetitions);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public LoopDefiner<E, DefaultDefiner<E>> loop(int minRepetitions, Integer maxRepetitions) {
        LoopDefiner<E, DefaultDefiner<E>> definer = new LoopDefiner<>(this, minRepetitions, maxRepetitions);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between() {
        BetweenDefiner<E, DefaultDefiner<E>> definer = new BetweenDefiner<>(this);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between(int repetitions) {
        BetweenDefiner<E, DefaultDefiner<E>> definer = new BetweenDefiner<>(this, repetitions);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public BetweenDefiner<E, DefaultDefiner<E>> between(int minRepetitions, Integer maxRepetitions) {
        BetweenDefiner<E, DefaultDefiner<E>> definer = new BetweenDefiner<>(this, minRepetitions, maxRepetitions);
        matchers.add(definer.matcher());
        return definer;
    }

    @Override
    public Definer<E> size(int length) {
        matchers.add(new SizeMatcher<>(this, length));
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
    public DefaultDefiner<E> characters(char[] values, int length) {
        matchers.add(new CharacterMatcher<>(this, values, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> characters(char[] values, Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, values, minLength, maxLength));
        return this;
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
    public DefaultDefiner<E> spaces(int length) {
        matchers.add(new CharacterMatcher<>(this, ' ', length));
        return this;
    }

    @Override
    public DefaultDefiner<E> spaces(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, ' ', minLength, maxLength));
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
    public DefaultDefiner<E> whitespaces(int length) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> whitespaces(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.WHITESPACE, minLength, maxLength));
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
    public DefaultDefiner<E> digits(int length) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> digits(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.DIGIT, minLength, maxLength));
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
    public DefaultDefiner<E> lowercases(int length) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> lowercases(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.LOWERCASE, minLength, maxLength));
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
    public DefaultDefiner<E> uppercases(int length) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> uppercases(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.UPPERCASE, minLength, maxLength));
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
    public DefaultDefiner<E> alphabets(int length) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphabets(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHABET, minLength, maxLength));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphanumeric() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHANUMERIC, 1));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphanumerics() {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHANUMERIC, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphanumerics(int length) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHANUMERIC, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> alphanumerics(Integer minLength, Integer maxLength) {
        matchers.add(new CharacterMatcher<>(this, CharacterMatcher.ALPHANUMERIC, minLength, maxLength));
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
    public Definer<E> quote() {
        matchers.add(new QuoteMatcher<>(this));
        return this;
    }

    @Override
    public Definer<E> quote(char... types) {
        matchers.add(new QuoteMatcher<>(this, types));
        return this;
    }

    @Override
    public DefaultDefiner<E> equalsWith(String value) {
        matchers.add(new EqualsMatcher<>(this, value));
        return this;
    }

    @Override
    public DefaultDefiner<E> equalsWith(String... values) {
        return equalsWith(Arrays.asList(values));
    }

    @Override
    public DefaultDefiner<E> equalsWith(List<String> values) {
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
    public DefaultDefiner<E> pattern(String regex, Integer minLength, Integer maxLength) {
        matchers.add(new RegexMatcher<>(this, regex, 0, minLength, maxLength));
        return this;
    }

    @Override
    public DefaultDefiner<E> pattern(Pattern pattern) {
        matchers.add(new RegexMatcher<>(this, pattern, null, null));
        return this;
    }

    @Override
    public DefaultDefiner<E> pattern(Pattern pattern, int length) {
        matchers.add(new RegexMatcher<>(this, pattern, length, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> pattern(Pattern pattern, Integer minLength, Integer maxLength) {
        matchers.add(new RegexMatcher<>(this, pattern, minLength, maxLength));
        return this;
    }

    @Override
    public DefaultDefiner<E> match(MatchFunction<E> matcher) {
        matchers.add(new MatchFunctionMatcher<>(this, matcher));
        return this;
    }

    @Override
    public DefaultDefiner<E> check(Function<String, Boolean> checker) {
        matchers.add(new BoolFunctionMatcher<>(this, checker));
        return this;
    }

    @Override
    public DefaultDefiner<E> check(Function<String, Boolean> checker, int length) {
        matchers.add(new BoolFunctionMatcher<>(this, checker, length));
        return this;
    }

    @Override
    public DefaultDefiner<E> check(Function<String, Boolean> checker, Integer minLength, Integer maxLength) {
        matchers.add(new BoolFunctionMatcher<>(this, checker, minLength, maxLength));
        return this;
    }

    @Override
    public DefaultDefiner<E> element(Element<E> element) {
        matchers.add(new UnregisteredMatcher<>(this, element));
        return this;
    }

    @Override
    public DefaultDefiner<E> element(Element<E>... elements) {
        return element(Arrays.asList(elements));
    }

    @Override
    public DefaultDefiner<E> element(List<Element<E>> elements) {
        matchers.add(new UnregisteredMatcher<>(this, elements));
        return this;
    }

    @Override
    public DefaultDefiner<E> elements() {
        matchers.add(new RegisteredMatcher<>(this));
        return this;
    }

    @Override
    public DefaultDefiner<E> include(String element) {
        matchers.add(new RegisteredMatcher<>(this, element));
        return this;
    }

    @Override
    public DefaultDefiner<E> include(String... elements) {
        return include(Arrays.asList(elements));
    }

    public DefaultDefiner<E> include(List<String> elements) {
        matchers.add(new RegisteredMatcher<>(this, elements));
        return this;
    }

    @Override
    public Definer<E> exclude(String element) {
        matchers.add(new ExcludeMatcher<>(this, element));
        return this;
    }

    @Override
    public Definer<E> exclude(String... elements) {
        return exclude(Arrays.asList(elements));
    }

    @Override
    public Definer<E> exclude(List<String> elements) {
        matchers.add(new ExcludeMatcher<>(this, elements));
        return this;
    }
}
