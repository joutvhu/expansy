package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Variable extends Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .equals("$")
                .name("name")
                .pattern("^[a-zA-Z_$][a-zA-Z_0-9]*$");
    }

    @Override
    public String create(Params<String> params) {
        return "Variable(" + params.getString("name") + ")";
    }
}
