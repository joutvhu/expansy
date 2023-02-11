package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.func.AnalyzerMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.EqualsMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.FunctionMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.RegexMatcher;
import com.joutvhu.dynamic.expression.analysis.match.func.RepeatMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DefaultMatcher<E> extends Matcher<E> {
    private String name;
    List<Matcher<E>> matchers = new ArrayList<>();

    @Override
    public MatchFunctions<E> name(String name) {
        this.name = name;
        return this;
    }

    String takeName() {
        String result = this.name;
        this.name = null;
        return result;
    }

    @Override
    public Matcher<E> space() {
        return add(new EqualsMatcher<>(this, " "));
    }

    @Override
    public Matcher<E> spaces() {
        return add(new RepeatMatcher<>(this, " ", 0, null));
    }

    @Override
    public Matcher<E> spaces(int time) {
        return add(new RepeatMatcher<>(this, " ", 0, time));
    }

    @Override
    public Matcher<E> equals(String value) {
        return add(new EqualsMatcher<>(this, value));
    }

    @Override
    public Matcher<E> equals(List<String> values) {
        return add(new EqualsMatcher<>(this, values));
    }

    @Override
    public Matcher<E> maybe(String value) {
        return add(new RepeatMatcher<>(this, value, 0, 1));
    }

    @Override
    public Matcher<E> repeat(String value, int time) {
        return add(new RepeatMatcher<>(this, value, 0, time));
    }

    @Override
    public Matcher<E> repeat(String value, int minTime, int maxTime) {
        return add(new RepeatMatcher<>(this, value, minTime, maxTime));
    }

    @Override
    public Matcher<E> match(String regex) {
        return add(new RegexMatcher<>(this, regex, null));
    }

    @Override
    public Matcher<E> match(String regex, int length) {
        return add(new RegexMatcher<>(this, regex, length));
    }

    @Override
    public Matcher<E> match(Function<String, Boolean> checker) {
        return add(new FunctionMatcher<>(this, checker));
    }

    @Override
    public Matcher<E> analyzer(String analyzerName) {
        return null;
    }

    @Override
    public Matcher<E> analyzer(List<String> analyzerNames) {
        return null;
    }

    @Override
    public Matcher<E> is(ElementAnalyzer<E> elementAnalyzer) {
        return add(new AnalyzerMatcher<E>(this, elementAnalyzer));
    }

    @Override
    public Matcher<E> is(List<ElementAnalyzer<E>> elementAnalyzers) {
        return add(new AnalyzerMatcher<E>(this, elementAnalyzers));
    }

    @Override
    public LoopMatcher<E> loop(int time) {
        return add(new LoopMatcher<>(this, time));
    }

    @Override
    public LoopMatcher<E> loop(int minTime, int maxTime) {
        return add(new LoopMatcher<>(this, minTime, maxTime));
    }

    private <T extends Matcher<E>> T add(T matcher) {
        this.matchers.add(matcher);
        return matcher;
    }
}
