package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;

public class Variable<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .equals("$")
                .name("name")
                .pattern("^[a-zA-Z_$][a-zA-Z_0-9]*$");
    }
}
