package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

import java.util.Arrays;
import java.util.List;

public class AnalyzerMatcher<E> extends MatchFunction<E> {
    private List<Element<E>> elements;

    public AnalyzerMatcher(Matcher<E> parent, Element<E> element) {
        super(parent);
        this.elements = Arrays.asList(element);
    }

    public AnalyzerMatcher(Matcher<E> parent, List<Element<E>> elements) {
        super(parent);
        this.elements = elements;
    }
}
