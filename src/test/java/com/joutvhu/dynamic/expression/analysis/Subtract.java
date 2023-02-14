package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;

public class Subtract<E> extends Element<E> {
    @Override
    public void analysis(DefaultMatcher<E> matcher) {
        matcher
                .name("first")
                .analyzerIs(new Number<>())
                .equals("-")
                .name("second")
                .analyzerIs(new Number<>());
    }
}
