package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

public class WordMatcher<E> extends Matcher<E> {
    public WordMatcher(Definer<E> parent) {
        super(parent);
    }

    public WordMatcher(String name, Definer<E> parent) {
        super(name, parent);
    }

    @Override
    public void match(LinearFilter filter) {

    }
}
