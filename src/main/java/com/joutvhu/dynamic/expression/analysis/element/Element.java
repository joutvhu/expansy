package com.joutvhu.dynamic.expression.analysis.element;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;

public abstract class Element<E> {
    public abstract void analysis(DefaultMatcher<E> matcher);

    // todo public abstract E create();
}
