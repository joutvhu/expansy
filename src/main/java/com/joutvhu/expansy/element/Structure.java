package com.joutvhu.expansy.element;

import java.util.Collection;

public abstract class Structure<E> {
    protected ElementRegister<E> register;

    public void setRegister(ElementRegister<E> register) {
        this.register = register;
    }

    public abstract Collection<Element<E>> next(Branch<E> branch);
}
