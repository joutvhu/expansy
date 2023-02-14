package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

import java.util.function.Function;

public class FunctionMatcher<E> extends MatchFunction<E> {
    private Function<String, Boolean> checker;

    public FunctionMatcher(Matcher<E> parent, Function<String, Boolean> checker) {
        super(parent);
        this.checker = checker;
    }

    @Override
    public void match(LinearFilter filter) {

    }
}
