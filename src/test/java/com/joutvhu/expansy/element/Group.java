package com.joutvhu.expansy.element;

import com.joutvhu.expansy.match.Definer;

public class Group extends Element<String> {
    @Override
    public void define(Definer<String> definer) {
        definer
                .equals("(")
                .name("operator")
                .elements()
                .equals(")");
    }

    @Override
    public String create(Params<String> params) {
        return "Group(" + params.getParams("operator").create() + ")";
    }
}
