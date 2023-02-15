package com.joutvhu.dynamic.expression.analysis.match.definer;

import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;

public final class MaybeDefiner<E, T extends Definer<E>> extends BreakDefiner<E, T> {
    public MaybeDefiner(Definer<E> parent) {
        super(parent);
    }

    Matcher<E> getMatcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(LinearFilter filter) {

            }
        };
    }
}
