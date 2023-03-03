package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.type.MatchFunction;

public class MatchFunctionMatcher<E> extends Matcher<E> {
    private MatchFunction<E> checker;

    public MatchFunctionMatcher(Definer<E> parent, MatchFunction<E> checker) {
        super(parent);
        if (checker == null)
            throw new DefineException("The checker must be non-null.");
        this.checker = checker;
    }

    @Override
    public void match(Consumer<E> consumer) {
        checker.match(consumer);
    }
}