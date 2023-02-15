package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunctions;

import java.util.List;
import java.util.function.Function;

public final class NamedDefiner<E, T extends Definer<E>> implements MatchFunctions<E, T> {
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
    public LoopDefiner<E, T> loop(int time) {
        return new LoopDefiner<>(parent, time);
    }

    @Override
    public LoopDefiner<E, T> loop(int minTime, Integer maxTime) {
        return new LoopDefiner<>(parent, minTime, maxTime);
    }

    @Override
    public BetweenDefiner<E, T> between() {
        return new BetweenDefiner<>(parent);
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
        return null;
    }

    @Override
    public T characters(char... values) {
        return null;
    }

    @Override
    public T characters(char[] values, int time) {
        return null;
    }

    @Override
    public T whitespace() {
        return null;
    }

    @Override
    public T whitespaces() {
        return null;
    }

    @Override
    public T whitespaces(int time) {
        return null;
    }

    @Override
    public T digit() {
        return null;
    }

    @Override
    public T digits() {
        return null;
    }

    @Override
    public T digits(int time) {
        return null;
    }

    @Override
    public T lowercase() {
        return null;
    }

    @Override
    public T lowercases() {
        return null;
    }

    @Override
    public T lowercases(int time) {
        return null;
    }

    @Override
    public T uppercase() {
        return null;
    }

    @Override
    public T uppercases() {
        return null;
    }

    @Override
    public T uppercases(int time) {
        return null;
    }

    @Override
    public T alphabet() {
        return null;
    }

    @Override
    public T alphabets() {
        return null;
    }

    @Override
    public T alphabets(int time) {
        return null;
    }

    @Override
    public T numeric() {
        return null;
    }

    @Override
    public T word() {
        return null;
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
    public T match(String regex) {
        parent.match(regex);
        return parent;
    }

    @Override
    public T match(String regex, int length) {
        parent.match(regex, length);
        return parent;
    }

    @Override
    public T match(Function<String, Boolean> checker) {
        parent.match(checker);
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
