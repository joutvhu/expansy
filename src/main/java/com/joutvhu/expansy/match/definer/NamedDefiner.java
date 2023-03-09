package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.type.MatchFunction;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public final class NamedDefiner<E, T extends Definer<E>> implements Matches<E, T> {
    protected String name;
    protected T parent;
    protected Definer<E> container;

    public NamedDefiner(T parent, String name) {
        this.parent = parent;
        this.container = parent;
        this.name = name;
    }

    public NamedDefiner(T parent, String name, Definer<E> container) {
        this.parent = parent;
        this.container = container;
        this.name = name;
    }

    private <R extends Definer<E>> R dub(Definer<E> definer) {
        Matcher<E> matcher = container.matcher();
        matcher.setName(name);
        return (R) definer;
    }

    @Override
    public MaybeDefiner<E, T> maybe() {
        return this.dub(parent.maybe());
    }

    @Override
    public OrDefiner<E, T> or() {
        if (parent instanceof OrDefiner)
            throw new DefineException("Unable to name a selection of OrDefiner.");
        return this.dub(parent.or());
    }

    @Override
    public LoopDefiner<E, T> loop() {
        return this.dub(parent.loop());
    }

    @Override
    public LoopDefiner<E, T> loop(int repetitions) {
        return this.dub(parent.loop(repetitions));
    }

    @Override
    public LoopDefiner<E, T> loop(int minRepetitions, Integer maxRepetitions) {
        return this.dub(parent.loop(minRepetitions, maxRepetitions));
    }

    @Override
    public BetweenDefiner<E, T> between() {
        return this.dub(parent.between());
    }

    @Override
    public BetweenDefiner<E, T> between(int repetitions) {
        return this.dub(parent.between(repetitions));
    }

    @Override
    public BetweenDefiner<E, T> between(int minRepetitions, Integer maxRepetitions) {
        return this.dub(parent.between(minRepetitions, maxRepetitions));
    }

    @Override
    public T size(int length) {
        this.dub(parent.size(length));
        return parent;
    }

    @Override
    public T character(char... values) {
        this.dub(parent.character(values));
        return parent;
    }

    @Override
    public T characters(char... values) {
        this.dub(parent.characters(values));
        return parent;
    }

    @Override
    public T characters(char[] values, int length) {
        this.dub(parent.characters(values, length));
        return parent;
    }

    @Override
    public T characters(char[] values, Integer minLength, Integer maxLength) {
        this.dub(parent.characters(values, minLength, maxLength));
        return parent;
    }

    @Override
    public T space() {
        this.dub(parent.space());
        return parent;
    }

    @Override
    public T spaces() {
        this.dub(parent.spaces());
        return parent;
    }

    @Override
    public T spaces(int length) {
        this.dub(parent.spaces(length));
        return parent;
    }

    @Override
    public T spaces(Integer minLength, Integer maxLength) {
        this.dub(parent.spaces(minLength, maxLength));
        return parent;
    }

    @Override
    public T whitespace() {
        this.dub(parent.whitespace());
        return parent;
    }

    @Override
    public T whitespaces() {
        this.dub(parent.whitespaces());
        return parent;
    }

    @Override
    public T whitespaces(int length) {
        this.dub(parent.whitespaces(length));
        return parent;
    }

    @Override
    public T whitespaces(Integer minLength, Integer maxLength) {
        this.dub(parent.whitespaces(minLength, maxLength));
        return parent;
    }

    @Override
    public T digit() {
        this.dub(parent.digit());
        return parent;
    }

    @Override
    public T digits() {
        this.dub(parent.digits());
        return parent;
    }

    @Override
    public T digits(int length) {
        this.dub(parent.digits(length));
        return parent;
    }

    @Override
    public T digits(Integer minLength, Integer maxLength) {
        this.dub(parent.digits(minLength, maxLength));
        return parent;
    }

    @Override
    public T lowercase() {
        this.dub(parent.lowercase());
        return parent;
    }

    @Override
    public T lowercases() {
        this.dub(parent.lowercases());
        return parent;
    }

    @Override
    public T lowercases(int length) {
        this.dub(parent.lowercases(length));
        return parent;
    }

    @Override
    public T lowercases(Integer minLength, Integer maxLength) {
        this.dub(parent.lowercases(minLength, maxLength));
        return parent;
    }

    @Override
    public T uppercase() {
        this.dub(parent.uppercase());
        return parent;
    }

    @Override
    public T uppercases() {
        this.dub(parent.uppercases());
        return parent;
    }

    @Override
    public T uppercases(int length) {
        this.dub(parent.uppercases(length));
        return parent;
    }

    @Override
    public T uppercases(Integer minLength, Integer maxLength) {
        this.dub(parent.uppercases(minLength, maxLength));
        return parent;
    }

    @Override
    public T alphabet() {
        this.dub(parent.alphabet());
        return parent;
    }

    @Override
    public T alphabets() {
        this.dub(parent.alphabets());
        return parent;
    }

    @Override
    public T alphabets(int length) {
        this.dub(parent.alphabets(length));
        return parent;
    }

    @Override
    public T alphabets(Integer minLength, Integer maxLength) {
        this.dub(parent.alphabets(minLength, maxLength));
        return parent;
    }

    @Override
    public T alphanumeric() {
        this.dub(parent.alphanumeric());
        return parent;
    }

    @Override
    public T alphanumerics() {
        this.dub(parent.alphanumerics());
        return parent;
    }

    @Override
    public T alphanumerics(int length) {
        this.dub(parent.alphanumerics(length));
        return parent;
    }

    @Override
    public T alphanumerics(Integer minLength, Integer maxLength) {
        this.dub(parent.alphanumerics(minLength, maxLength));
        return parent;
    }

    @Override
    public T numeric() {
        this.dub(parent.numeric());
        return parent;
    }

    @Override
    public T word() {
        this.dub(parent.word());
        return parent;
    }

    @Override
    public T quote() {
        this.dub(parent.quote());
        return parent;
    }

    @Override
    public T quote(char... types) {
        this.dub(parent.quote(types));
        return parent;
    }

    @Override
    public T equals(String value) {
        this.dub(parent.equals(value));
        return parent;
    }

    @Override
    public T equals(String... values) {
        this.dub(parent.equals(values));
        return parent;
    }

    @Override
    public T equals(List<String> values) {
        this.dub(parent.equals(values));
        return parent;
    }

    @Override
    public T equalsIgnoreCase(String value) {
        this.dub(parent.equalsIgnoreCase(value));
        return parent;
    }

    @Override
    public T equalsIgnoreCase(String... values) {
        this.dub(parent.equalsIgnoreCase(values));
        return parent;
    }

    @Override
    public T equalsIgnoreCase(List<String> values) {
        this.dub(parent.equalsIgnoreCase(values));
        return parent;
    }

    @Override
    public T pattern(String regex) {
        this.dub(parent.pattern(regex));
        return parent;
    }

    @Override
    public T pattern(String regex, int length) {
        this.dub(parent.pattern(regex, length));
        return parent;
    }

    @Override
    public T pattern(String regex, Integer minLength, Integer maxLength) {
        this.dub(parent.pattern(regex, minLength, maxLength));
        return parent;
    }

    @Override
    public T pattern(Pattern pattern) {
        this.dub(parent.pattern(pattern));
        return parent;
    }

    @Override
    public T pattern(Pattern pattern, int length) {
        this.dub(parent.pattern(pattern, length));
        return parent;
    }

    @Override
    public T pattern(Pattern pattern, Integer minLength, Integer maxLength) {
        this.dub(parent.pattern(pattern, minLength, maxLength));
        return parent;
    }

    @Override
    public T match(MatchFunction<E> matcher) {
        this.dub(parent.match(matcher));
        return parent;
    }

    @Override
    public T check(Function<String, Boolean> checker) {
        this.dub(parent.check(checker));
        return parent;
    }

    @Override
    public T check(Function<String, Boolean> checker, int length) {
        this.dub(parent.check(checker, length));
        return parent;
    }

    @Override
    public T check(Function<String, Boolean> checker, Integer minLength, Integer maxLength) {
        this.dub(parent.check(checker, minLength, maxLength));
        return parent;
    }

    @Override
    public T element(Element<E> element) {
        this.dub(parent.element(element));
        return parent;
    }

    @Override
    public T element(Element<E>... elements) {
        this.dub(parent.element(elements));
        return parent;
    }

    @Override
    public T element(List<Element<E>> elements) {
        this.dub(parent.element(elements));
        return parent;
    }

    @Override
    public T elements() {
        this.dub(parent.elements());
        return parent;
    }

    @Override
    public T include(String element) {
        this.dub(parent.include(element));
        return parent;
    }

    @Override
    public T include(String... elements) {
        this.dub(parent.include(elements));
        return parent;
    }

    @Override
    public T include(List<String> elements) {
        this.dub(parent.include(elements));
        return parent;
    }

    @Override
    public T exclude(String element) {
        this.dub(parent.exclude(element));
        return parent;
    }

    @Override
    public T exclude(String... elements) {
        this.dub(parent.exclude(elements));
        return parent;
    }

    @Override
    public T exclude(List<String> elements) {
        this.dub(parent.exclude(elements));
        return parent;
    }
}
