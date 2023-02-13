package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public interface MatchFunctions<E, T extends Matcher<E>> {
    MaybeMatcher<E, ?> maybe();

    LoopMatcher<E, ?> loop(int time);

    LoopMatcher<E, ?> loop(int minTime, Integer maxTime);

    /**
     * Matches any space character
     */
    T space();

    T spaces();

    T spaces(int time);

    T character(char... values);

    T characters(char... values);

    T characters(char[] values, int time);

    /**
     * Matches any whitespace character (spaces, tabs, line breaks)
     */
    T whitespace();

    T whitespaces();

    T whitespaces(int time);

    /**
     * Matches any digit character (0-9). Equivalent to [0-9].
     */
    T digit();

    T digits();

    T digits(int time);

    /**
     * Matches any lowercase character
     */
    T lowercase();

    T lowercases();

    T lowercases(int time);

    /**
     * Matches any uppercase character
     */
    T uppercase();

    T uppercases();

    T uppercases(int time);

    /**
     * Matches any alphabet character
     */
    T alphabet();

    T alphabets();

    T alphabets(int time);

    T numeric();

    T word();

    T equals(String value);

    T equals(String... values);

    T equals(List<String> values);

    T match(String regex);

    T match(String regex, int length);

    T match(Function<String, Boolean> checker);

    T analyzerName(String analyzerName);

    T analyzerName(String... analyzerNames);

    T analyzerName(List<String> analyzerNames);

    T analyzerIs(ElementAnalyzer<E> elementAnalyzer);

    T analyzerIs(ElementAnalyzer<E>... elementAnalyzers);

    T analyzerIs(List<ElementAnalyzer<E>> elementAnalyzers);
}
