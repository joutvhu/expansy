package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

public class Subtract<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .name("first")
                .element(new Number<>())
                .equals("-")
                .name("second")
                .element(new Number<>());
    }
}
