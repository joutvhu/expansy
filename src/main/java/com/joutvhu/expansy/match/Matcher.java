package com.joutvhu.expansy.match;

import com.joutvhu.expansy.match.filter.LinearFilter;

public abstract class Matcher<E> {
    protected String name;
    protected Definer<E> parent;

    public Matcher(Definer<E> parent) {
        this.parent = parent;
    }

    public Matcher(String name, Definer<E> parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void match(LinearFilter<E> filter);
}
