package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.definer.BetweenDefiner;
import com.joutvhu.dynamic.expression.analysis.match.definer.LoopDefiner;
import com.joutvhu.dynamic.expression.analysis.match.definer.MaybeDefiner;

import java.util.List;
import java.util.function.Function;

public interface MatchFunctions<E, T extends Definer<E>> {
    MaybeDefiner<E, ?> maybe();

    LoopDefiner<E, ?> loop();

    LoopDefiner<E, ?> loop(int repetitions);

    LoopDefiner<E, ?> loop(int minRepetitions, Integer maxRepetitions);

    BetweenDefiner<E, ?> between();

    BetweenDefiner<E, ?> between(int repetitions);

    BetweenDefiner<E, ?> between(int minRepetitions, Integer maxRepetitions);

    /**
     * Matches any space character
     */
    T space();

    T spaces();

    T spaces(int time);

    T character(char... values);

    T characters(char... values);

    T characters(char[] values, int repetitions);

    /**
     * Matches any whitespace character (spaces, tabs, line breaks)
     */
    T whitespace();

    T whitespaces();

    T whitespaces(int repetitions);

    /**
     * Matches any digit character (0-9). Equivalent to [0-9].
     */
    T digit();

    T digits();

    T digits(int repetitions);

    /**
     * Matches any lowercase character
     */
    T lowercase();

    T lowercases();

    T lowercases(int repetitions);

    /**
     * Matches any uppercase character
     */
    T uppercase();

    T uppercases();

    T uppercases(int repetitions);

    /**
     * Matches any alphabet character
     */
    T alphabet();

    T alphabets();

    T alphabets(int repetitions);

    T numeric();

    T word();

    T equals(String value);

    T equals(String... values);

    T equals(List<String> values);

    T match(String regex);

    T match(String regex, int length);

    T match(Function<String, Boolean> checker);

    T elementName(String elementName);

    T elementName(String... elementNames);

    T elementName(List<String> elementNames);

    T element(Element<E> element);

    T element(Element<E>... elements);

    T element(List<Element<E>> elements);
}
