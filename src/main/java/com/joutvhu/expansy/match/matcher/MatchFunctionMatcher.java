package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.type.MatchFunction;

public class MatchFunctionMatcher<E> extends Matcher<E> {
    private final MatchFunction<E> matcher;

    public MatchFunctionMatcher(Definer<E> parent, MatchFunction<E> matcher) {
        super(parent);
        if (matcher == null)
            throw new DefineException("The matcher must be non-null.");
        this.matcher = matcher;
    }

    @Override
    public void match(Consumer<E> consumer) {
        matcher.match(consumer);
    }
}