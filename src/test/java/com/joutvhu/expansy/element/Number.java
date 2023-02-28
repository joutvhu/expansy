package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Number extends Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .name("value")
                .pattern("^-?[0-9]+(\\.[0-9]+)?$");
    }

    @Override
    public String render(Node<String> node) {
        return "Number(" + node.getAsString("value") + ")";
    }
}
