package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class MultiplyAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public void analysis(Matcher<E> matcher) {
        matcher
                .name("first")
                .is(new NumberAnalyzer<>())
                .equals("*")
                .name("second")
                .is(new NumberAnalyzer<>());
    }
}
