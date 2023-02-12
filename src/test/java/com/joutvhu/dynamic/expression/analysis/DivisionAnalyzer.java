package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;

public class DivisionAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public void analysis(DefaultMatcher<E> matcher) {
        matcher
                .name("first")
                .analyzerIs(new NumberAnalyzer<>())
                .equals("/")
                .name("second")
                .analyzerIs(new NumberAnalyzer<>());
    }
}
