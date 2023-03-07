package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class MultiplyDivision extends Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .name("first")
                .exclude("AddSubtract")
                .spaces()
                .name("operator")
                .equals("/", "*")
                .spaces()
                .name("second")
                .exclude("AddSubtract", "MultiplyDivision");
    }

    @Override
    public String render(Node<String> node) {
        String first = node.getNode("first").render();
        String second = node.getNode("second").render();
        return "MD(" + first + node.getAsString("operator") + second + ")";
    }
}
