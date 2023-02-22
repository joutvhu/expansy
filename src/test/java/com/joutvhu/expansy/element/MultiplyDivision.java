package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class MultiplyDivision<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .name("first")
                .elements()
                .spaces()
                .equals("/", "*")
                .spaces()
                .name("second")
                .elements();
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
