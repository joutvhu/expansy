package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class VariableAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public void analysis(Matcher<E> matcher) {
        matcher
                .equals("$")
                .name("name")
                .match("^[a-zA-Z_$][a-zA-Z_0-9]*$");
    }
}
