package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Number<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .name("value")
                .pattern("^-?[0-9]+(.[0-9]+)?$");
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
