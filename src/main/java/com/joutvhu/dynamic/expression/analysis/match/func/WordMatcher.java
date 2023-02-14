package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class WordMatcher<E> extends MatchFunction<E> {
    public WordMatcher(Matcher<E> parent) {
        super(parent);
    }

    public WordMatcher(String name, Matcher<E> parent) {
        super(name, parent);
    }
}
