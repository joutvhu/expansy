package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;

public abstract class ExpressionRegister<E> {
    public abstract void register(String name, Element<E> element);
}
