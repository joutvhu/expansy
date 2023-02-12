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

    T match(String regex);

    T match(String regex, int length);

    T match(Function<String, Boolean> checker);

    T analyzerName(String analyzerName);

    T analyzerName(String... analyzerNames);

    T analyzerName(List<String> analyzerNames);

    T analyzerIs(ElementAnalyzer<E> elementAnalyzer);

    T analyzerIs(ElementAnalyzer<E>... elementAnalyzers);

    T analyzerIs(List<ElementAnalyzer<E>> elementAnalyzers);

    LoopMatcher<E, ?> loop(int time);

    LoopMatcher<E, ?> loop(int minTime, Integer maxTime);
}
