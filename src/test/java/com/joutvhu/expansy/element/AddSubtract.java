package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class AddSubtract implements Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .name("first")
                .elements()
                .spaces()
                .name("operator")
                .equalsWith("+", "-")
                .spaces()
                .name("second")
                .exclude("AddSubtract");
    }

    @Override
    public String render(Node<String> node) {
        String first = node.getNode("first").render();
        String second = node.getNode("second").render();
        return "AS(" + first + node.getAsString("operator") + second + ")";
    }
}
