package com.joutvhu.dynamic.expression.analysis.match.function;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

public class EqualsMatcher<E> extends ProxyMatcher<E> {
    private String value;

    public EqualsMatcher(DefaultMatcher<E> parent, String value) {
        super(parent);
        this.value = value;
    }
}
