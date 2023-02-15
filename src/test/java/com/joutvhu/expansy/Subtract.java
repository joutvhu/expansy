package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;

public class Subtract<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .name("first")
                .element(new Number<>())
                .spaces()
                .equals("-")
                .spaces()
                .name("second")
                .element(new Number<>());
    }
}
