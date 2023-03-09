package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Variable implements Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .equals("$")
                .name("name")
                .pattern("^[a-zA-Z_$][a-zA-Z_0-9]*$");
    }

    @Override
    public String render(Node<String> node) {
        return "V(" + node.getAsString("name") + ")";
    }
}
