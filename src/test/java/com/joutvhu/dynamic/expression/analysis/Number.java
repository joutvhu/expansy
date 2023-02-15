package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

public class Number<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .name("value")
                .pattern("^-?[0-9]+(.[0-9]+)?$");
    }
}
