package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

import java.util.List;
import java.util.function.Function;

public interface MatchFunctions<E, T extends Matcher<E>> {
    T space();

    T spaces();

    T spaces(int time);

    T equals(String value);

    T equals(String... values);

    T equals(List<String> values);

    T maybe(String value);

    T repeat(String value, int time);

    T repeat(String value, int minTime, int maxTime);

    T match(String regex);

    T match(String regex, int length);

    T match(Function<String, Boolean> checker);

    T analyzer(String analyzerName);

    T analyzer(String... analyzerNames);

    T analyzer(List<String> analyzerNames);

    T is(ElementAnalyzer<E> elementAnalyzer);

    T is(ElementAnalyzer<E>... elementAnalyzers);

    T is(List<ElementAnalyzer<E>> elementAnalyzers);

    LoopMatcher<E, ?> loop(int time);

    LoopMatcher<E, ?> loop(int minTime, Integer maxTime);
}
