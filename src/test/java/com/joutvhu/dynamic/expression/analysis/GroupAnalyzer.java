package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class GroupAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public void analysis(Matcher<E> matcher) {
        matcher
                .equals("(")
                // todo: match child elements
                .equals(")");
    }
}
