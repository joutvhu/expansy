package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;

public final class MaybeDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, MaybeDefiner<E, T>> {
    private T parent;

    public MaybeDefiner(T parent) {
        this.parent = parent;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(LinearFilter<E> filter) {

            }
        };
    }

    public T end() {
        return parent;
    }
}
