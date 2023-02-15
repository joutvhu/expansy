package com.joutvhu.dynamic.expression.analysis.match;

import com.joutvhu.dynamic.expression.analysis.match.definer.Matches;

public interface Definer<E> extends Matches<E, Definer<E>> {
    Matches<E, ?> name(String name);

    Matcher<E> matcher();
}
