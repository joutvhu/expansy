package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.type.MatchFunction;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class ProxyDefiner<E, T extends Definer<E>> implements Definer<E> {
    protected T master;
    protected Definer<E> container;

    public ProxyDefiner() {
        this.master = (T) this;
        this.container = new DefaultDefiner<>();
    }

    public ProxyDefiner(T master) {
        this.master = master;
        this.container = new DefaultDefiner<>();
    }

    public ProxyDefiner(T master, Definer<E> container) {
        this.master = master;
        this.container = container;
    }

    protected List<Matcher<E>> matchers() {
        return DefinerUtil.matchersOf(container);
    }

    @Override
    public Matches<E, T> name(String name) {
        return new NamedDefiner<>(master, name, container);
    }

    private <R extends Definer<E>> R add(R definer) {
        matchers().add(definer.matcher());
        return definer;
    }

    @Override
    public MaybeDefiner<E, T> maybe() {
        return add(new MaybeDefiner<>(master));
    }

    @Override
    public OrDefiner<E, ?> or() {
        return add(new OrDefiner<>(master));
    }

    @Override
    public LoopDefiner<E, T> loop() {
        return add(new LoopDefiner<>(master));
    }

    @Override
    public LoopDefiner<E, T> loop(int repetitions) {
        return add(new LoopDefiner<>(master, repetitions));
    }

    @Override
    public LoopDefiner<E, T> loop(int minRepetitions, Integer maxRepetitions) {
        return add(new LoopDefiner<>(master, minRepetitions, maxRepetitions));
    }

    @Override
    public BetweenDefiner<E, T> between() {
        return add(new BetweenDefiner<>(master));
    }

    @Override
    public BetweenDefiner<E, T> between(int repetitions) {
        return add(new BetweenDefiner<>(master, repetitions));
    }

    @Override
    public BetweenDefiner<E, T> between(int minRepetitions, Integer maxRepetitions) {
        return add(new BetweenDefiner<>(master, minRepetitions, maxRepetitions));
    }

    @Override
    public T size(int length) {
        container.size(length);
        return master;
    }

    @Override
    public T character(char... values) {
        container.character(values);
        return master;
    }

    @Override
    public T characters(char... values) {
        container.characters(values);
        return master;
    }

    @Override
    public T characters(char[] values, int length) {
        container.characters(values, length);
        return master;
    }

    @Override
    public T characters(char[] values, Integer minLength, Integer maxLength) {
        container.characters(values, minLength, maxLength);
        return master;
    }

    @Override
    public T space() {
        container.space();
        return master;
    }

    @Override
    public T spaces() {
        container.spaces();
        return master;
    }

    @Override
    public T spaces(int length) {
        container.spaces(length);
        return master;
    }

    @Override
    public T spaces(Integer minLength, Integer maxLength) {
        container.spaces(minLength, maxLength);
        return master;
    }

    @Override
    public T whitespace() {
        container.whitespace();
        return master;
    }

    @Override
    public T whitespaces() {
        container.whitespaces();
        return master;
    }

    @Override
    public T whitespaces(int length) {
        container.whitespaces(length);
        return master;
    }

    @Override
    public T whitespaces(Integer minLength, Integer maxLength) {
        container.whitespaces(minLength, maxLength);
        return master;
    }

    @Override
    public T digit() {
        container.digit();
        return master;
    }

    @Override
    public T digits() {
        container.digits();
        return master;
    }

    @Override
    public T digits(int length) {
        container.digits(length);
        return master;
    }

    @Override
    public T digits(Integer minLength, Integer maxLength) {
        container.digits(minLength, maxLength);
        return master;
    }

    @Override
    public T lowercase() {
        container.lowercase();
        return master;
    }

    @Override
    public T lowercases() {
        container.lowercases();
        return master;
    }

    @Override
    public T lowercases(int length) {
        container.lowercases(length);
        return master;
    }

    @Override
    public T lowercases(Integer minLength, Integer maxLength) {
        container.lowercases(minLength, maxLength);
        return master;
    }

    @Override
    public T uppercase() {
        container.uppercase();
        return master;
    }

    @Override
    public T uppercases() {
        container.uppercases();
        return master;
    }

    @Override
    public T uppercases(int length) {
        container.uppercases(length);
        return master;
    }

    @Override
    public T uppercases(Integer minLength, Integer maxLength) {
        container.uppercases(minLength, maxLength);
        return master;
    }

    @Override
    public T alphabet() {
        container.alphabet();
        return master;
    }

    @Override
    public T alphabets() {
        container.alphabets();
        return master;
    }

    @Override
    public T alphabets(int length) {
        container.alphabets(length);
        return master;
    }

    @Override
    public T alphabets(Integer minLength, Integer maxLength) {
        container.alphabets(minLength, maxLength);
        return master;
    }

    @Override
    public T alphanumeric() {
        container.alphanumeric();
        return master;
    }

    @Override
    public T alphanumerics() {
        container.alphanumerics();
        return master;
    }

    @Override
    public T alphanumerics(int length) {
        container.alphanumerics(length);
        return master;
    }

    @Override
    public T alphanumerics(Integer minLength, Integer maxLength) {
        container.alphanumerics(minLength, maxLength);
        return master;
    }

    @Override
    public T numeric() {
        container.numeric();
        return master;
    }

    @Override
    public T word() {
        container.word();
        return master;
    }

    @Override
    public T quote() {
        container.quote();
        return master;
    }

    @Override
    public T quote(char... types) {
        container.quote(types);
        return master;
    }

    @Override
    public T equals(String value) {
        container.equals(value);
        return master;
    }

    @Override
    public T equals(String... values) {
        container.equals(values);
        return master;
    }

    @Override
    public T equals(List<String> values) {
        container.equals(values);
        return master;
    }

    @Override
    public T equalsIgnoreCase(String value) {
        container.equalsIgnoreCase(value);
        return master;
    }

    @Override
    public T equalsIgnoreCase(String... values) {
        container.equalsIgnoreCase(values);
        return master;
    }

    @Override
    public T equalsIgnoreCase(List<String> values) {
        container.equalsIgnoreCase(values);
        return master;
    }

    @Override
    public T pattern(String regex) {
        container.pattern(regex);
        return master;
    }

    @Override
    public T pattern(String regex, int length) {
        container.pattern(regex, length);
        return master;
    }

    @Override
    public T pattern(String regex, Integer minLength, Integer maxLength) {
        container.pattern(regex, minLength, maxLength);
        return master;
    }

    @Override
    public T pattern(Pattern pattern) {
        container.pattern(pattern);
        return master;
    }

    @Override
    public T pattern(Pattern pattern, int length) {
        container.pattern(pattern, length);
        return master;
    }

    @Override
    public T pattern(Pattern pattern, Integer minLength, Integer maxLength) {
        container.pattern(pattern, minLength, maxLength);
        return master;
    }

    @Override
    public T match(MatchFunction<E> matcher) {
        container.match(matcher);
        return master;
    }

    @Override
    public T check(Function<String, Boolean> checker) {
        container.check(checker);
        return master;
    }

    @Override
    public T check(Function<String, Boolean> checker, int length) {
        container.check(checker, length);
        return master;
    }

    @Override
    public T check(Function<String, Boolean> checker, Integer minLength, Integer maxLength) {
        container.check(checker, minLength, maxLength);
        return master;
    }

    @Override
    public T element(Element<E> element) {
        container.element(element);
        return master;
    }

    @Override
    public T element(Element<E>... elements) {
        container.element(elements);
        return master;
    }

    @Override
    public T element(List<Element<E>> elements) {
        container.element(elements);
        return master;
    }

    @Override
    public T elements() {
        container.elements();
        return master;
    }

    @Override
    public T include(String element) {
        container.include(element);
        return master;
    }

    @Override
    public T include(String... elements) {
        container.include(elements);
        return master;
    }

    @Override
    public T include(List<String> elements) {
        container.include(elements);
        return master;
    }

    @Override
    public T exclude(String element) {
        container.exclude(element);
        return master;
    }

    @Override
    public T exclude(String... elements) {
        container.exclude(elements);
        return master;
    }

    @Override
    public T exclude(List<String> elements) {
        container.exclude(elements);
        return master;
    }
}
