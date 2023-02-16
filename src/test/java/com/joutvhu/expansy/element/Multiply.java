package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Multiply<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .name("first")
                .element(new Number<>())
                .spaces()
                .equals("*")
                .spaces()
                .name("second")
                .element(new Number<>());
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
