package com.joutvhu.expansy.element;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.match.Definer;

public class Variable<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .equals("$")
                .name("name")
                .pattern("^[a-zA-Z_$][a-zA-Z_0-9]*$");
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
