package com.joutvhu.dynamic.expression.analysis.match.function;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

public class RegexMatcher<E> extends ProxyMatcher<E> {
    private String regex;

    public RegexMatcher(DefaultMatcher<E> parent, String regex) {
        super(parent);
        this.regex = regex;
    }
}
