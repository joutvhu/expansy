package com.joutvhu.dynamic.expression.analysis.element;

import com.joutvhu.dynamic.expression.analysis.match.definer.DefaultDefiner;

public abstract class Element<E> {
    public abstract void define(DefaultDefiner<E> definer);

    // todo public abstract E create();
}
