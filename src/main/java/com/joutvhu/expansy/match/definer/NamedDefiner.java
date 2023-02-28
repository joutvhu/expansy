package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;

import java.util.List;
import java.util.function.Function;

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
    public T spaces(int time) {
        this.dub(parent.spaces(time));
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
    public T characters(char[] values, int repetitions) {
        this.dub(parent.characters(values, repetitions));
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
    public T whitespaces(int repetitions) {
        this.dub(parent.whitespaces(repetitions));
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
    public T digits(int repetitions) {
        this.dub(parent.digits(repetitions));
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
    public T lowercases(int repetitions) {
        this.dub(parent.lowercases(repetitions));
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
    public T uppercases(int repetitions) {
        this.dub(parent.uppercases(repetitions));
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
    public T alphabets(int repetitions) {
        this.dub(parent.alphabets(repetitions));
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
    public T pattern(String regex, int minLength, int maxLength) {
        this.dub(parent.pattern(regex, minLength, maxLength));
        return parent;
    }

    @Override
    public T check(Function<String, Boolean> checker) {
        this.dub(parent.check(checker));
        return parent;
    }

    @Override
    public T element(String element) {
        this.dub(parent.element(element));
        return parent;
    }

    @Override
    public T element(String... elements) {
        this.dub(parent.element(elements));
        return parent;
    }

    public T element(List<String> elements) {
        this.dub(parent.element(elements));
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
    public T elements() {
        this.dub(parent.elements());
        return parent;
    }
}
