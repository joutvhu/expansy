package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class AddSubtract extends Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .name("first")
                .elements()
                .spaces()
                .name("operator")
                .equals("+", "-")
                .spaces()
                .name("second")
                .elements();
    }

    @Override
    public String render(Node<String> node) {
        String first = node.getParams("first").render();
        String second = node.getParams("second").render();
        return "AddSubtract(" + first + node.getString("operator") + second + ")";
    }
}
