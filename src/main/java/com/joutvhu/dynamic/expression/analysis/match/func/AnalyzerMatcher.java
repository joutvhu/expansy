package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

import java.util.Arrays;
import java.util.List;

public class AnalyzerMatcher<E> extends MatchFunction<E> {
    private List<ElementAnalyzer<E>> elementAnalyzers;

    public AnalyzerMatcher(Matcher<E> parent, ElementAnalyzer<E> elementAnalyzer) {
        super(parent);
        this.elementAnalyzers = Arrays.asList(elementAnalyzer);
    }

    public AnalyzerMatcher(Matcher<E> parent, List<ElementAnalyzer<E>> elementAnalyzers) {
        super(parent);
        this.elementAnalyzers = elementAnalyzers;
    }
}
