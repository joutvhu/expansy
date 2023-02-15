package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;

public class Number<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .name("value")
                .pattern("^-?[0-9]+(.[0-9]+)?$");
    }
}
