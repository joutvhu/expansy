package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

public class RegexMatcher<E> extends MatchFunction<E> {
    private String regex;
    private Integer length;

    public RegexMatcher(Matcher<E> parent, String regex, Integer length) {
        super(parent);
        this.regex = regex;
        this.length = length;
    }
}
