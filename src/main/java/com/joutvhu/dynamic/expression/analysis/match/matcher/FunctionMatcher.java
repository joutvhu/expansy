package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

import java.util.function.Function;

public class FunctionMatcher<E> extends Matcher<E> {
    private Function<String, Boolean> checker;

    public FunctionMatcher(Definer<E> parent, Function<String, Boolean> checker) {
        super(parent);
        this.checker = checker;
    }

    @Override
    public void match(LinearFilter filter) {

    }
}
