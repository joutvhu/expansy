package com.joutvhu.dynamic.expression.analysis.parser;

import com.joutvhu.dynamic.expression.analysis.element.Element;

public abstract class ExpressionRegister<E> {
    public abstract void register(String name, Element<E> element);
}
