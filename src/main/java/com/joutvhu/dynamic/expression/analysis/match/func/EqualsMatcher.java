package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;
import com.joutvhu.dynamic.expression.analysis.match.ProxyMatcher;

import java.util.Arrays;
import java.util.List;

public class EqualsMatcher<E> extends ProxyMatcher<E> {
    private List<String> values;

    public EqualsMatcher(DefaultMatcher<E> parent, String value) {
        super(parent);
        this.values = Arrays.asList(value);
    }

    public EqualsMatcher(DefaultMatcher<E> parent, List<String> values) {
        super(parent);
        this.values = values;
    }
}
