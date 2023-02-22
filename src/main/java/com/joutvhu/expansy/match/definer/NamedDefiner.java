package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;

import java.util.List;
import java.util.function.Function;

public final class NamedDefiner<E, T extends Definer<E>> implements Matches<E, T> {
    protected String name;
    protected T parent;

    public NamedDefiner(T parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public MaybeDefiner<E, T> maybe() {
        return new MaybeDefiner<>(parent);
    }

    @Override
    public LoopDefiner<E, T> loop() {
        return new LoopDefiner<>(parent);
    }

    @Override
    public LoopDefiner<E, T> loop(int repetitions) {
        return new LoopDefiner<>(parent, repetitions);
    }

    @Override
    public LoopDefiner<E, T> loop(int minRepetitions, Integer maxRepetitions) {
        return new LoopDefiner<>(parent, minRepetitions, maxRepetitions);
    }

    @Override
    public BetweenDefiner<E, T> between() {
        return new BetweenDefiner<>(parent);
    }

    @Override
    public BetweenDefiner<E, T> between(int repetitions) {
        return new BetweenDefiner<>(parent, repetitions);
    }

    @Override
    public BetweenDefiner<E, T> between(int minRepetitions, Integer maxRepetitions) {
        return new BetweenDefiner<>(parent, minRepetitions, maxRepetitions);
    }
    
    private T result() {
        Matcher<E> matcher = parent.matcher();
        matcher.setName(name);
        return result();
    }

    @Override
    public T space() {
        parent.space();
        return result();
    }

    @Override
    public T spaces() {
        parent.spaces();
        return result();
    }

    @Override
    public T spaces(int time) {
        parent.spaces(time);
        return result();
    }

    @Override
    public T character(char... values) {
        parent.character(values);
        return result();
    }

    @Override
    public T characters(char... values) {
        parent.characters(values);
        return result();
    }

    @Override
    public T characters(char[] values, int repetitions) {
        parent.characters(values, repetitions);
        return result();
    }

    @Override
    public T whitespace() {
        parent.whitespace();
        return result();
    }

    @Override
    public T whitespaces() {
        parent.whitespaces();
        return result();
    }

    @Override
    public T whitespaces(int repetitions) {
        parent.whitespaces(repetitions);
        return result();
    }

    @Override
    public T digit() {
        parent.digit();
        return result();
    }

    @Override
    public T digits() {
        parent.digits();
        return result();
    }

    @Override
    public T digits(int repetitions) {
        parent.digits(repetitions);
        return result();
    }

    @Override
    public T lowercase() {
        parent.lowercase();
        return result();
    }

    @Override
    public T lowercases() {
        parent.lowercases();
        return result();
    }

    @Override
    public T lowercases(int repetitions) {
        parent.lowercases(repetitions);
        return result();
    }

    @Override
    public T uppercase() {
        parent.uppercase();
        return result();
    }

    @Override
    public T uppercases() {
        parent.uppercases();
        return result();
    }

    @Override
    public T uppercases(int repetitions) {
        parent.uppercases(repetitions);
        return result();
    }

    @Override
    public T alphabet() {
        parent.alphabet();
        return result();
    }

    @Override
    public T alphabets() {
        parent.alphabets();
        return result();
    }

    @Override
    public T alphabets(int repetitions) {
        parent.alphabets(repetitions);
        return result();
    }

    @Override
    public T numeric() {
        parent.numeric();
        return result();
    }

    @Override
    public T word() {
        parent.word();
        return result();
    }

    @Override
    public T equals(String value) {
        parent.equals(value);
        return result();
    }

    @Override
    public T equals(String... values) {
        parent.equals(values);
        return result();
    }

    @Override
    public T equals(List<String> values) {
        parent.equals(values);
        return result();
    }

    @Override
    public T equalsIgnoreCase(String value) {
        parent.equalsIgnoreCase(value);
        return result();
    }

    @Override
    public T equalsIgnoreCase(String... values) {
        parent.equalsIgnoreCase(values);
        return result();
    }

    @Override
    public T equalsIgnoreCase(List<String> values) {
        parent.equalsIgnoreCase(values);
        return result();
    }

    @Override
    public T pattern(String regex) {
        parent.pattern(regex);
        return result();
    }

    @Override
    public T pattern(String regex, int length) {
        parent.pattern(regex, length);
        return result();
    }

    @Override
    public T pattern(String regex, int minLength, int maxLength) {
        parent.pattern(regex, minLength, maxLength);
        return result();
    }

    @Override
    public T check(Function<String, Boolean> checker) {
        parent.check(checker);
        return result();
    }

    @Override
    public T element(String element) {
        parent.element(element);
        return result();
    }

    @Override
    public T element(String... elements) {
        parent.element(elements);
        return result();
    }

    public T element(List<String> elements) {
        parent.element(elements);
        return result();
    }

    @Override
    public T element(Element<E> element) {
        parent.element(element);
        return result();
    }

    @Override
    public T element(Element<E>... elements) {
        parent.element(elements);
        return result();
    }

    @Override
    public T elements() {
        parent.elements();
        return result();
    }
}
