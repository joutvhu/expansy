package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.definer.DefaultDefiner;

public class Group<E> extends Element<E> {
    @Override
    public void define(DefaultDefiner<E> matcher) {
        matcher
                .equals("(")
                // todo: match child elements
                .equals(")");
    }
}
