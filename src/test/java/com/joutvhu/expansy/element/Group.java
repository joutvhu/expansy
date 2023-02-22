package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Group<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .equals("(")
                .elements()
                .equals(")");
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
