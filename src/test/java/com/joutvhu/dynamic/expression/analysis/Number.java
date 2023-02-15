package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.definer.DefaultDefiner;

public class Number<E> extends Element<E> {
    @Override
    public void define(DefaultDefiner<E> matcher) {
        matcher
                .name("value")
                .match("^-?[0-9]+(.[0-9]+)?$");
    }
}
