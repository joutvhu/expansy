package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

public class RepeatMatcher<E> extends Matcher<E> {
    private String value;
    private Integer minTime;
    private Integer maxTime;

    public RepeatMatcher(Definer<E> parent, String value, Integer minTime, Integer maxTime) {
        super(parent);
        this.value = value;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public void match(LinearFilter filter) {

    }
}
