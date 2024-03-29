package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Number implements Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .name("value")
                .pattern("^-?[0-9]+(\\.[0-9]+)?$");
    }

    @Override
    public String render(Node<String> node) {
        return "N(" + node.getAsString("value") + ")";
    }
}
