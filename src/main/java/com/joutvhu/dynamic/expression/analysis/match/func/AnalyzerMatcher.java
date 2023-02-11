package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

import java.util.Arrays;
import java.util.List;

public class AnalyzerMatcher<E> extends ProxyMatcher<E> {
    private List<ElementAnalyzer<E>> elementAnalyzers;

    public AnalyzerMatcher(DefaultMatcher<E> parent, ElementAnalyzer<E> elementAnalyzer) {
        super(parent);
        this.elementAnalyzers = Arrays.asList(elementAnalyzer);
    }

    public AnalyzerMatcher(DefaultMatcher<E> parent, List<ElementAnalyzer<E>> elementAnalyzers) {
        super(parent);
        this.elementAnalyzers = elementAnalyzers;
    }
}
