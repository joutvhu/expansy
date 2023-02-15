package com.joutvhu.expansy.match;

import com.joutvhu.expansy.match.definer.Matches;

public interface Definer<E> extends Matches<E, Definer<E>> {
    Matches<E, ?> name(String name);

    Matcher<E> matcher();
}
