package com.joutvhu.expansy.match;

import com.joutvhu.expansy.match.consumer.Consumer;

public abstract class Matcher<E> {
    protected String name;
    protected Definer<E> parent;

    public Matcher(Definer<E> parent) {
        this(parent, null);
    }

    public Matcher(Definer<E> parent, String name) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void match(Consumer<E> consumer);
}
