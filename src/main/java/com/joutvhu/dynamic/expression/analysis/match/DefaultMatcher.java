package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.func.AnalyzerMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.EqualsMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.FunctionMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.RegexMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.RepeatMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DefaultMatcher<E> implements Matcher<E> {
    private String name;
    List<MatchFunction<E>> matchers = new ArrayList<>();

    @Override
    public MatchFunctions<E, DefaultMatcher<E>> name(String name) {
        this.name = name;
        return new ProxyMatcher<>(this, name);
    }

    @Override
    public MaybeMatcher<E, DefaultMatcher<E>> maybe() {
        return new MaybeMatcher<>(this);
    }

    @Override
    public LoopMatcher<E, DefaultMatcher<E>> loop(int time) {
        LoopMatcher<E, DefaultMatcher<E>> matcher = new LoopMatcher<>(this, time);
        matchers.add(matcher);
        return matcher;
    }

    @Override
    public LoopMatcher<E, DefaultMatcher<E>> loop(int minTime, Integer maxTime) {
        LoopMatcher<E, DefaultMatcher<E>> matcher = new LoopMatcher<>(this, minTime, maxTime);
        matchers.add(matcher);
        return matcher;
    }

    @Override
    public DefaultMatcher<E> space() {
        matchers.add(new EqualsMatcher<>(this, " "));
        return this;
    }

    @Override
    public DefaultMatcher<E> spaces() {
        matchers.add(new RepeatMatcher<>(this, " ", 0, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> spaces(int time) {
        matchers.add(new RepeatMatcher<>(this, " ", 0, time));
        return this;
    }

    @Override
    public DefaultMatcher<E> equals(String value) {
        matchers.add(new EqualsMatcher<>(this, value));
        return this;
    }

    @Override
    public DefaultMatcher<E> equals(String... values) {
        return equals(Arrays.asList(values));
    }

    @Override
    public DefaultMatcher<E> equals(List<String> values) {
        matchers.add(new EqualsMatcher<>(this, values));
        return this;
    }

    @Override
    public DefaultMatcher<E> match(String regex) {
        matchers.add(new RegexMatcher<>(this, regex, null));
        return this;
    }

    @Override
    public DefaultMatcher<E> match(String regex, int length) {
        matchers.add(new RegexMatcher<>(this, regex, length));
        return this;
    }

    @Override
    public DefaultMatcher<E> match(Function<String, Boolean> checker) {
        matchers.add(new FunctionMatcher<>(this, checker));
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerName(String analyzerName) {
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerName(List<String> analyzerNames) {
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerName(String... analyzerNames) {
        return analyzerName(Arrays.asList(analyzerNames));
    }

    @Override
    public DefaultMatcher<E> analyzerIs(ElementAnalyzer<E> elementAnalyzer) {
        matchers.add(new AnalyzerMatcher<>(this, elementAnalyzer));
        return this;
    }

    @Override
    public DefaultMatcher<E> analyzerIs(ElementAnalyzer<E>... elementAnalyzers) {
        return analyzerIs(Arrays.asList(elementAnalyzers));
    }

    @Override
    public DefaultMatcher<E> analyzerIs(List<ElementAnalyzer<E>> elementAnalyzers) {
        matchers.add(new AnalyzerMatcher<>(this, elementAnalyzers));
        return this;
    }
}
