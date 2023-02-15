package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;

public class Group<E> extends Element<E> {
    @Override
    public void define(Definer<E> matcher) {
        matcher
                .equals("(")
                // todo: match child elements
                .equals(")");
    }
}
