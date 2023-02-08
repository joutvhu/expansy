package com.joutvhu.dynamic.expression.analysis.match.function;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

public class RegexMatcher<E> extends ProxyMatcher<E> {
    private String regex;
    private Integer length;

    public RegexMatcher(DefaultMatcher<E> parent, String regex, Integer length) {
        super(parent);
        this.regex = regex;
        this.length = length;
    }
}
