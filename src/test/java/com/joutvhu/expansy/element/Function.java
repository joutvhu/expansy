package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

import java.util.Collection;
import java.util.stream.Collectors;

public class Function extends Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .equals("#")
                .name("name")
                .pattern("^[a-zA-Z_$][a-zA-Z_0-9]*$")
                .equals("(")
                .between()
                .spaces()
                .name("param")
                .elements()
                .spaces()
                .is()
                .equals(",")
                .end()
                .equals(")");
    }

    @Override
    public String render(Node<String> node) {
        Collection<Node<String>> p = node.getAllParams("param");
        String pr = p.stream().map(v -> v.render()).collect(Collectors.joining(", "));
        return "Function(" + node.getString("name") + "(" + pr + "))";
    }
}
