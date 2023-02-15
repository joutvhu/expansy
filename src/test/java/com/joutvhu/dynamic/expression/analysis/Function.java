package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.definer.DefaultDefiner;

public class Function<E> extends Element<E> {
    @Override
    public void define(DefaultDefiner<E> matcher) {
        matcher
                .equals("#")
                .name("name")
                .match("^[a-zA-Z_$][a-zA-Z_0-9]*$")
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
