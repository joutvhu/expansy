package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
        if (container instanceof DefaultDefiner)
            return ((DefaultDefiner<E>) container).matchers;
        return new ArrayList<>();
    }

    @Override
    public Matches<E, T> name(String name) {
        return new NamedDefiner<>(master, name, container);
    }

    private <R extends Definer<E>> R add(R definer) {
        if (container instanceof DefaultDefiner)
            ((DefaultDefiner<E>) container).matchers.add(definer.matcher());
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
    public T spaces(int time) {
        container.spaces(time);
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
    public T characters(char[] values, int repetitions) {
        container.characters(values, repetitions);
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
    public T whitespaces(int repetitions) {
        container.whitespaces(repetitions);
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
    public T digits(int repetitions) {
        container.digits(repetitions);
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
    public T lowercases(int repetitions) {
        container.lowercases(repetitions);
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
    public T uppercases(int repetitions) {
        container.uppercases(repetitions);
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
    public T alphabets(int repetitions) {
        container.alphabets(repetitions);
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
    public T pattern(String regex, int minLength, int maxLength) {
        container.pattern(regex, minLength, maxLength);
        return master;
    }

    @Override
    public T check(Function<String, Boolean> checker) {
        container.check(checker);
        return master;
    }

    @Override
    public T element(String element) {
        container.element(element);
        return master;
    }

    @Override
    public T element(String... elements) {
        container.element(elements);
        return master;
    }

    public T element(List<String> elements) {
        container.element(elements);
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
    public T elements() {
        container.elements();
        return master;
    }
}
