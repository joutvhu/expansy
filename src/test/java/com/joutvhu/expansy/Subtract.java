package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.match.Definer;

public class Subtract<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .name("first")
                .element(new Number<>())
                .spaces()
                .equals("-")
                .spaces()
                .name("second")
                .element(new Number<>());
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
