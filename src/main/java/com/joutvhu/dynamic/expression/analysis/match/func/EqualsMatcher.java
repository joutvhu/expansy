package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

import java.util.Arrays;
import java.util.List;

public class EqualsMatcher<E> extends MatchFunction<E> {
    private List<String> values;

    public EqualsMatcher(Matcher<E> parent, String value) {
        super(parent);
        this.values = Arrays.asList(value);
    }

    public EqualsMatcher(Matcher<E> parent, List<String> values) {
        super(parent);
        this.values = values;
    }

    @Override
    public void match(LinearFilter filter) {

    }
}
