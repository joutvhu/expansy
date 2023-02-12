package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;

public class FunctionAnalyzer<E> extends ElementAnalyzer<E> {
    @Override
    public void analysis(DefaultMatcher<E> matcher) {
        matcher
                .equals("#")
                .name("name")
                .match("^[a-zA-Z_$][a-zA-Z_0-9]*$")
                .equals("(")
                .loop(0, null)
                .name("param")
                .analyzerIs(new VariableAnalyzer<>())
                .end()
                .equals(")");
    }
}
