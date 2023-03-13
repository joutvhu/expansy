package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.type.MatchFunction;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * List all match functions
 *
 * @author Giao Ho
 * @since 1.0.0
 */
@SuppressWarnings("java:S1452")
public interface Matches<E, T extends Definer<E>> {
    /**
     * Define a matcher group that may or may not appear.
     */
    MaybeDefiner<E, ?> maybe();

    /**
     * Could match with a group in the matcher groups.
     */
    OrDefiner<E, ?> or();

    /**
     * Used to define a matcher group it is repeatable.
     */
    LoopDefiner<E, ?> loop();

    LoopDefiner<E, ?> loop(int repetitions);

    LoopDefiner<E, ?> loop(int minRepetitions, Integer maxRepetitions);

    /**
     * Used to define a matcher group it can repeat and be interspersed by another matcher group.
     */
    BetweenDefiner<E, ?> between();

    BetweenDefiner<E, ?> between(int repetitions);

    BetweenDefiner<E, ?> between(int minRepetitions, Integer maxRepetitions);

    /**
     * Matches a string of characters of the specified {@param length}
     */
    T size(int length);

    /**
     * Matches one character contained in the {@param characters} array.
     */
    T character(char... characters);

    /**
     * Matches multiple characters contained in the {@param characters} array.
     */
    T characters(char... characters);

    /**
     * Matches {@param length} characters, each of which must be contained in the {@param characters} array.
     */
    T characters(char[] characters, int length);

    /**
     * Matches a minimum of {@param minLength} characters and a maximum of {@param maxLength} characters, each of which must be contained in the {@param characters} array.
     */
    T characters(char[] characters, Integer minLength, Integer maxLength);

    /**
     * Matches one space character.
     */
    T space();

    /**
     * Matches multiple space characters.
     */
    T spaces();

    /**
     * Matches {@param length} space characters.
     */
    T spaces(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} space characters.
     */
    T spaces(Integer minLength, Integer maxLength);

    /**
     * Matches one whitespace character (spaces, tabs or line breaks).
     */
    T whitespace();

    /**
     * Matches multiple whitespace characters.
     */
    T whitespaces();

    /**
     * Matches {@param length} whitespace characters.
     */
    T whitespaces(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} whitespace characters.
     */
    T whitespaces(Integer minLength, Integer maxLength);

    /**
     * Matches one digit character (0-9).
     */
    T digit();

    /**
     * Matches multiple digit characters.
     */
    T digits();

    /**
     * Matches {@param length} digit characters.
     */
    T digits(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} digit characters.
     */
    T digits(Integer minLength, Integer maxLength);

    /**
     * Matches one lowercase character.
     */
    T lowercase();

    /**
     * Matches multiple lowercase characters.
     */
    T lowercases();

    /**
     * Matches {@param length} lowercase characters.
     */
    T lowercases(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} lowercase characters.
     */
    T lowercases(Integer minLength, Integer maxLength);

    /**
     * Matches one uppercase character.
     */
    T uppercase();

    /**
     * Matches multiple uppercase characters.
     */
    T uppercases();

    /**
     * Matches {@param length} uppercase characters.
     */
    T uppercases(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} uppercase characters.
     */
    T uppercases(Integer minLength, Integer maxLength);

    /**
     * Matches one alphabet character
     */
    T alphabet();

    /**
     * Matches multiple alphabet characters.
     */
    T alphabets();

    /**
     * Matches {@param length} alphabet characters.
     */
    T alphabets(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} alphabet characters.
     */
    T alphabets(Integer minLength, Integer maxLength);

    /**
     * Matches one alphanumeric character
     */
    T alphanumeric();

    /**
     * Matches multiple alphanumeric characters.
     */
    T alphanumerics();

    /**
     * Matches {@param length} alphanumeric characters.
     */
    T alphanumerics(int length);

    /**
     * Matches a minimum of {@param minLength} space characters and a maximum of {@param maxLength} alphanumeric characters.
     */
    T alphanumerics(Integer minLength, Integer maxLength);

    /**
     * Matches a numeric /^-?[0-9]+(.[0-9]+)?^/
     */
    T numeric();

    /**
     * Matches a word (any alphanumeric or underscore).
     */
    T word();

    /**
     * Matches a quote with quote type are " or '.
     */
    T quote();

    /**
     * Matches a quote with specified quote type.
     */
    T quote(char... types);

    /**
     * Matches the specified strings.
     */
    T equalsWith(String value);

    /**
     * Matches the specified strings.
     */
    T equalsWith(String... values);

    /**
     * Matches the specified strings.
     */
    T equalsWith(List<String> values);

    /**
     * Matches the specified strings but ignore case.
     */
    T equalsIgnoreCase(String value);

    /**
     * Matches the specified strings but ignore case.
     */
    T equalsIgnoreCase(String... values);

    /**
     * Matches the specified strings but ignore case.
     */
    T equalsIgnoreCase(List<String> values);

    /**
     * Matches the specified pattern.
     */
    T pattern(String regex);

    T pattern(String regex, int length);

    T pattern(String regex, Integer minLength, Integer maxLength);

    /**
     * Matches the specified pattern.
     */
    T pattern(Pattern pattern);

    T pattern(Pattern pattern, int length);

    T pattern(Pattern pattern, Integer minLength, Integer maxLength);

    /**
     * Use a matcher to match
     */
    T match(MatchFunction<E> matcher);

    /**
     * Use a method to match
     */
    T check(Function<String, Boolean> checker);

    T check(Function<String, Boolean> checker, int length);

    T check(Function<String, Boolean> checker, Integer minLength, Integer maxLength);

    /**
     * Matches the provided elements
     */
    T element(Element<E> element);

    T element(Element<E>... elements);

    T element(List<Element<E>> elements);

    /**
     * Matches with all registered elements
     */
    T elements();

    /**
     * Matches registered elements with the specified name
     */
    T include(String element);

    T include(String... elements);

    T include(List<String> elements);

    /**
     * Matches registered elements except those listed
     */
    T exclude(String element);

    T exclude(String... elements);

    T exclude(List<String> elements);
}
