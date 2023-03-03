package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.type.MatchFunction;

import java.util.List;
import java.util.function.Function;

public interface Matches<E, T extends Definer<E>> {
    MaybeDefiner<E, ?> maybe();

    OrDefiner<E, ?> or();

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

    T spaces(int length);

    T spaces(Integer minLength, Integer maxLength);

    /**
     * Matches any characters provided.
     */
    T character(char... values);

    T characters(char... values);

    T characters(char[] values, int length);

    T characters(char[] values, Integer minLength, Integer maxLength);

    /**
     * Matches any whitespace character (spaces, tabs, line breaks)
     */
    T whitespace();

    T whitespaces();

    T whitespaces(int length);

    T whitespaces(Integer minLength, Integer maxLength);

    /**
     * Matches any digit character (0-9). Equivalent to [0-9].
     */
    T digit();

    T digits();

    T digits(int length);

    T digits(Integer minLength, Integer maxLength);

    /**
     * Matches any lowercase character
     */
    T lowercase();

    T lowercases();

    T lowercases(int length);

    T lowercases(Integer minLength, Integer maxLength);

    /**
     * Matches any uppercase character
     */
    T uppercase();

    T uppercases();

    T uppercases(int length);

    T uppercases(Integer minLength, Integer maxLength);

    /**
     * Matches any alphabet character
     */
    T alphabet();

    T alphabets();

    T alphabets(int length);

    T alphabets(Integer minLength, Integer maxLength);

    /**
     * Matches any alphanumeric character
     */
    T alphanumeric();

    T alphanumerics();

    T alphanumerics(int length);

    T alphanumerics(Integer minLength, Integer maxLength);

    T numeric();

    /**
     * Matches any word character (alphanumeric & underscore).
     */
    T word();

    T equals(String value);

    T equals(String... values);

    T equals(List<String> values);

    T equalsIgnoreCase(String value);

    T equalsIgnoreCase(String... values);

    T equalsIgnoreCase(List<String> values);

    T pattern(String regex);

    T pattern(String regex, int length);

    T pattern(String regex, Integer minLength, Integer maxLength);

    T check(Function<String, Boolean> checker);

    T check(MatchFunction<E> checker);

    /**
     * Matches all registered elements with the specified name
     */
    T element(String element);

    T element(String... elements);

    T element(List<String> elements);

    T element(Element<E> element);

    T element(Element<E>... elements);

    /**
     * Matches with all registered elements
     */
    T elements();
}
