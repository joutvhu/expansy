package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

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

    @Override
    public T space() {
        parent.space();
        return parent;
    }

    @Override
    public T spaces() {
        parent.spaces();
        return parent;
    }

    @Override
    public T spaces(int time) {
        parent.spaces(time);
        return parent;
    }

    @Override
    public T character(char... values) {
        parent.character(values);
        return parent;
    }

    @Override
    public T characters(char... values) {
        parent.characters(values);
        return parent;
    }

    @Override
    public T characters(char[] values, int repetitions) {
        parent.characters(values, repetitions);
        return parent;
    }

    @Override
    public T whitespace() {
        parent.whitespace();
        return parent;
    }

    @Override
    public T whitespaces() {
        parent.whitespaces();
        return parent;
    }

    @Override
    public T whitespaces(int repetitions) {
        parent.whitespaces(repetitions);
        return parent;
    }

    @Override
    public T digit() {
        parent.digit();
        return parent;
    }

    @Override
    public T digits() {
        parent.digits();
        return parent;
    }

    @Override
    public T digits(int repetitions) {
        parent.digits(repetitions);
        return parent;
    }

    @Override
    public T lowercase() {
        parent.lowercase();
        return parent;
    }

    @Override
    public T lowercases() {
        parent.lowercases();
        return parent;
    }

    @Override
    public T lowercases(int repetitions) {
        parent.lowercases(repetitions);
        return parent;
    }

    @Override
    public T uppercase() {
        parent.uppercase();
        return parent;
    }

    @Override
    public T uppercases() {
        parent.uppercases();
        return parent;
    }

    @Override
    public T uppercases(int repetitions) {
        parent.uppercases(repetitions);
        return parent;
    }

    @Override
    public T alphabet() {
        parent.alphabet();
        return parent;
    }

    @Override
    public T alphabets() {
        parent.alphabets();
        return parent;
    }

    @Override
    public T alphabets(int repetitions) {
        parent.alphabets(repetitions);
        return parent;
    }

    @Override
    public T numeric() {
        parent.numeric();
        return parent;
    }

    @Override
    public T word() {
        parent.word();
        return parent;
    }

    @Override
    public T equals(String value) {
        parent.equals(value);
        return parent;
    }

    @Override
    public T equals(String... values) {
        parent.equals(values);
        return parent;
    }

    @Override
    public T equals(List<String> values) {
        parent.equals(values);
        return parent;
    }

    @Override
    public T equalsIgnoreCase(String value) {
        parent.equalsIgnoreCase(value);
        return parent;
    }

    @Override
    public T equalsIgnoreCase(String... values) {
        parent.equalsIgnoreCase(values);
        return parent;
    }

    @Override
    public T equalsIgnoreCase(List<String> values) {
        parent.equalsIgnoreCase(values);
        return parent;
    }

    @Override
    public T pattern(String regex) {
        parent.pattern(regex);
        return parent;
    }

    @Override
    public T pattern(String regex, int length) {
        parent.pattern(regex, length);
        return parent;
    }

    @Override
    public T pattern(String regex, int minLength, int maxLength) {
        parent.pattern(regex, minLength, maxLength);
        return parent;
    }

    @Override
    public T check(Function<String, Boolean> checker) {
        parent.check(checker);
        return parent;
    }

    @Override
    public T elementName(String elementName) {
        parent.elementName(elementName);
        return parent;
    }

    @Override
    public T elementName(String... elementNames) {
        parent.elementName(elementNames);
        return parent;
    }

    @Override
    public T elementName(List<String> elementNames) {
        parent.elementName(elementNames);
        return parent;
    }

    @Override
    public T element(Element<E> element) {
        parent.element(element);
        return parent;
    }

    @Override
    public T element(Element<E>... elements) {
        parent.element(elements);
        return parent;
    }

    @Override
    public T element(List<Element<E>> elements) {
        parent.element(elements);
        return parent;
    }
}
