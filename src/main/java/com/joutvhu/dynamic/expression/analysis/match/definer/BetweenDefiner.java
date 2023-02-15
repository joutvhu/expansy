package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunctions;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;

import java.util.List;
import java.util.function.Function;

public class BetweenDefiner<E, T extends Definer<E>> implements Definer<E> {
    private Definer<E> parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;
    private DefaultDefiner<E> children;

    public BetweenDefiner(Definer<E> parent) {
        this(parent, 0, null);
    }

    public BetweenDefiner(Definer<E> parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public BetweenDefiner(Definer<E> parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        this.minRepetitions = minRepetitions == null || minRepetitions < 0 ? 0 : minRepetitions;
        this.maxRepetitions = maxRepetitions;
        this.children = new DefaultDefiner<>();
    }

    Matcher<E> getMatcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(LinearFilter filter) {

            }
        };
    }

    public IsDefiner<E, T> is() {
        return new IsDefiner<>(parent);
    }

    @Override
    public MatchFunctions<E, BetweenDefiner<E, T>> name(String name) {
        return new NamedDefiner<>(this, name);
    }

    @Override
    public MaybeDefiner<E, BetweenDefiner<E, T>> maybe() {
        return new MaybeDefiner<>(this);
    }

    @Override
    public LoopDefiner<E, BetweenDefiner<E, T>> loop() {
        return new LoopDefiner<>(this);
    }

    @Override
    public LoopDefiner<E, BetweenDefiner<E, T>> loop(int repetitions) {
        return new LoopDefiner<>(this, repetitions);
    }

    @Override
    public LoopDefiner<E, BetweenDefiner<E, T>> loop(int minRepetitions, Integer maxRepetitions) {
        return new LoopDefiner<>(this, minRepetitions, maxRepetitions);
    }

    @Override
    public BetweenDefiner<E, BetweenDefiner<E, T>> between() {
        return new BetweenDefiner<>(this);
    }

    @Override
    public BetweenDefiner<E, BetweenDefiner<E, T>> between(int repetitions) {
        return new BetweenDefiner<>(this, repetitions);
    }

    @Override
    public BetweenDefiner<E, BetweenDefiner<E, T>> between(int minRepetitions, Integer maxRepetitions) {
        return new BetweenDefiner<>(this, minRepetitions, maxRepetitions);
    }

    @Override
    public BetweenDefiner<E, T> space() {
        children.space();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> spaces() {
        children.spaces();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> spaces(int time) {
        children.spaces(time);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> character(char... values) {
        children.character(values);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> characters(char... values) {
        children.characters(values);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> characters(char[] values, int repetitions) {
        children.characters(values, repetitions);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> whitespace() {
        children.whitespace();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> whitespaces() {
        children.whitespaces();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> whitespaces(int repetitions) {
        children.whitespaces(repetitions);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> digit() {
        children.digit();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> digits() {
        children.digits();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> digits(int repetitions) {
        children.digits(repetitions);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> lowercase() {
        children.lowercase();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> lowercases() {
        children.lowercases();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> lowercases(int repetitions) {
        children.lowercases(repetitions);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> uppercase() {
        children.uppercase();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> uppercases() {
        children.uppercases();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> uppercases(int repetitions) {
        children.uppercases(repetitions);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> alphabet() {
        children.alphabet();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> alphabets() {
        children.alphabets();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> alphabets(int repetitions) {
        children.alphabets(repetitions);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> numeric() {
        children.numeric();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> word() {
        children.word();
        return this;
    }

    @Override
    public BetweenDefiner<E, T> equals(String value) {
        children.equals(value);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> equals(String... values) {
        children.equals(values);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> equals(List<String> values) {
        children.equals(values);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> match(String regex) {
        children.match(regex);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> match(String regex, int length) {
        children.match(regex, length);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> match(Function<String, Boolean> checker) {
        children.match(checker);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> elementName(String elementName) {
        children.elementName(elementName);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> elementName(String... elementNames) {
        children.elementName(elementNames);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> elementName(List<String> elementNames) {
        children.elementName(elementNames);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> element(Element<E> element) {
        children.element(element);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> element(Element<E>... elements) {
        children.element(elements);
        return this;
    }

    @Override
    public BetweenDefiner<E, T> element(List<Element<E>> elements) {
        children.element(elements);
        return this;
    }

    public class IsDefiner<E, T extends Definer<E>> extends BreakDefiner<E, T> {
        public IsDefiner(Definer<E> parent) {
            super(parent);
        }
    }
}
