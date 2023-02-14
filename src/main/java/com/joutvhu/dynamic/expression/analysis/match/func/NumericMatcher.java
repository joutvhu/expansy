package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class NumericMatcher<E> extends MatchFunction<E> {
    public NumericMatcher(Matcher<E> parent) {
        super(parent);
    }

    public NumericMatcher(String name, Matcher<E> parent) {
        super(name, parent);
    }
}
