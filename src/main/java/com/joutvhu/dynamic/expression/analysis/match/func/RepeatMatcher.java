package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.element.ElementAnalyzer;
import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.LoopMatcher;
import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

import java.util.List;

public class RepeatMatcher<E> extends MatchFunction<E> {
    private String value;
    private Integer minTime;
    private Integer maxTime;

    public RepeatMatcher(Matcher<E> parent, String value, Integer minTime, Integer maxTime) {
        super(parent);
        this.value = value;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }
}
