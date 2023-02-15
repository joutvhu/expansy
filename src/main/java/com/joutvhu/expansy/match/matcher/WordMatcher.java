package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;
import com.joutvhu.expansy.match.Definer;

public class WordMatcher<E> extends Matcher<E> {
    public WordMatcher(Definer<E> parent) {
        super(parent);
    }

    public WordMatcher(String name, Definer<E> parent) {
        super(name, parent);
    }

    @Override
    public void match(LinearFilter<E> filter) {

    }
}
