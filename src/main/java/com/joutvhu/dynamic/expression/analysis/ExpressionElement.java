package com.joutvhu.dynamic.expression.analysis;

public abstract class ExpressionElement<E> {
    public abstract String getName();

    public abstract E build();
}
