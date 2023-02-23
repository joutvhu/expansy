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
    public String create(Params<String> params) {
        String first = params.getParams("first").create();
        String second = params.getParams("second").create();
        return "AddSubtract(" + first + params.getString("operator") + second + ")";
    }
}
