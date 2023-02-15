package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.match.Definer;

public class Group<E> extends Element<E> {
    @Override
    public void define(Definer<E> definer) {
        definer
                .equals("(")
                // todo: match child elements
                .equals(")");
    }

    @Override
    public E create(Params params) {
        return null;
    }
}
