package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Group implements Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .equals("(")
                .name("operator")
                .elements()
                .equals(")");
    }

    @Override
    public String render(Node<String> node) {
        return "G(" + node.getNode("operator").render() + ")";
    }
}
