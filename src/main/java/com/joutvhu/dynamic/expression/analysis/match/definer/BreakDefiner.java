package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunctions;

import java.util.List;
import java.util.function.Function;

public abstract class BreakDefiner<E, T extends Definer<E>> implements Definer<E> {
    protected Definer<E> parent;
    protected DefaultDefiner<E> children;

    public BreakDefiner(Definer<E> parent) {
        this.parent = parent;
        this.children = new DefaultDefiner<>();
    }

    public T end() {
        return (T) parent;
    }

    @Override
    public MatchFunctions<E, BreakDefiner<E, T>> name(String name) {
        return new NamedDefiner<>(this, name);
    }

    @Override
    public MaybeDefiner<E, BreakDefiner<E, T>> maybe() {
        return new MaybeDefiner<>(this);
    }

    @Override
    public LoopDefiner<E, BreakDefiner<E, T>> loop(int time) {
        return new LoopDefiner<>(this, time);
    }

    @Override
    public LoopDefiner<E, BreakDefiner<E, T>> loop(int minTime, Integer maxTime) {
        return new LoopDefiner<>(this, minTime, maxTime);
    }

    @Override
    public BetweenDefiner<E, BreakDefiner<E, T>> between() {
        return new BetweenDefiner<>(this);
    }

    @Override
    public BreakDefiner<E, T> space() {
        children.space();
        return this;
    }

    @Override
    public BreakDefiner<E, T> spaces() {
        children.spaces();
        return this;
    }

    @Override
    public BreakDefiner<E, T> spaces(int time) {
        children.spaces(time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> character(char... values) {
        children.character(values);
        return this;
    }

    @Override
    public BreakDefiner<E, T> characters(char... values) {
        children.characters(values);
        return this;
    }

    @Override
    public BreakDefiner<E, T> characters(char[] values, int time) {
        children.characters(values, time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> whitespace() {
        children.whitespace();
        return this;
    }

    @Override
    public BreakDefiner<E, T> whitespaces() {
        children.whitespaces();
        return this;
    }

    @Override
    public BreakDefiner<E, T> whitespaces(int time) {
        children.whitespaces(time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> digit() {
        children.digit();
        return this;
    }

    @Override
    public BreakDefiner<E, T> digits() {
        children.digits();
        return this;
    }

    @Override
    public BreakDefiner<E, T> digits(int time) {
        children.digits(time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> lowercase() {
        children.lowercase();
        return this;
    }

    @Override
    public BreakDefiner<E, T> lowercases() {
        children.lowercases();
        return this;
    }

    @Override
    public BreakDefiner<E, T> lowercases(int time) {
        children.lowercases(time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> uppercase() {
        children.uppercase();
        return this;
    }

    @Override
    public BreakDefiner<E, T> uppercases() {
        children.uppercases();
        return this;
    }

    @Override
    public BreakDefiner<E, T> uppercases(int time) {
        children.uppercases(time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> alphabet() {
        children.alphabet();
        return this;
    }

    @Override
    public BreakDefiner<E, T> alphabets() {
        children.alphabets();
        return this;
    }

    @Override
    public BreakDefiner<E, T> alphabets(int time) {
        children.alphabets(time);
        return this;
    }

    @Override
    public BreakDefiner<E, T> numeric() {
        children.numeric();
        return this;
    }

    @Override
    public BreakDefiner<E, T> word() {
        children.word();
        return this;
    }

    @Override
    public BreakDefiner<E, T> equals(String value) {
        children.equals(value);
        return this;
    }

    @Override
    public BreakDefiner<E, T> equals(String... values) {
        children.equals(values);
        return this;
    }

    @Override
    public BreakDefiner<E, T> equals(List<String> values) {
        children.equals(values);
        return this;
    }

    @Override
    public BreakDefiner<E, T> match(String regex) {
        children.match(regex);
        return this;
    }

    @Override
    public BreakDefiner<E, T> match(String regex, int length) {
        children.match(regex, length);
        return this;
    }

    @Override
    public BreakDefiner<E, T> match(Function<String, Boolean> checker) {
        children.match(checker);
        return this;
    }

    @Override
    public BreakDefiner<E, T> elementName(String elementName) {
        children.elementName(elementName);
        return this;
    }

    @Override
    public BreakDefiner<E, T> elementName(String... elementNames) {
        children.elementName(elementNames);
        return this;
    }

    @Override
    public BreakDefiner<E, T> elementName(List<String> elementNames) {
        children.elementName(elementNames);
        return this;
    }

    @Override
    public BreakDefiner<E, T> element(Element<E> element) {
        children.element(element);
        return this;
    }

    @Override
    public BreakDefiner<E, T> element(Element<E>... elements) {
        children.element(elements);
        return this;
    }

    @Override
    public BreakDefiner<E, T> element(List<Element<E>> elements) {
        children.element(elements);
        return this;
    }
}
