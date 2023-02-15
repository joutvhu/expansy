package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public abstract class Element<E> {
    public abstract void define(Definer<E> definer);

    public abstract E create(Params params);
}
