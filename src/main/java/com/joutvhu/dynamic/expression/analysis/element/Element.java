package com.joutvhu.dynamic.expression.analysis.element;

import com.joutvhu.dynamic.expression.analysis.match.Definer;

public abstract class Element<E> {
    public abstract void define(Definer<E> definer);

    // todo public abstract E create();
}
