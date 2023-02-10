package com.joutvhu.dynamic.expression.analysis.match.function;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

public class RepeatMatcher<E> extends ProxyMatcher<E> {
    private String value;
    private Integer minTime;
    private Integer maxTime;

    public RepeatMatcher(DefaultMatcher<E> parent, String value, Integer minTime, Integer maxTime) {
        super(parent);
        this.value = value;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }
}
