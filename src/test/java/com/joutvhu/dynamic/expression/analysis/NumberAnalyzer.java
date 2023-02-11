package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class NumberAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public void analysis(DefaultMatcher<E> matcher) {
        matcher
                .name("value")
                .match("^-?[0-9]+(.[0-9]+)?$");
    }
}
