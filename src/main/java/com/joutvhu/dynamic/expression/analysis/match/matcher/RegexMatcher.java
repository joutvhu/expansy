package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

public class RegexMatcher<E> extends Matcher<E> {
    private String regex;
    private Integer length;

    public RegexMatcher(Definer<E> parent, String regex, Integer length) {
        super(parent);
        this.regex = regex;
        this.length = length;
    }

    @Override
    public void match(LinearFilter filter) {

    }
}
