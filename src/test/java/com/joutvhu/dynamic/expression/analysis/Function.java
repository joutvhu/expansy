package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

public class Function<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .equals("#")
                .name("name")
                .pattern("^[a-zA-Z_$][a-zA-Z_0-9]*$")
                .equals("(")
                .between()
                .name("param")
                .element(new Variable<>())
                .is()
                .equals(",")
                .end()
                .equals(")");
    }
}
