package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;

public abstract class ExpressionRegister<E> {
    public abstract void register(String name, ElementAnalyzer<E> elementAnalyzer);
}
