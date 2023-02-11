package com.joutvhu.dynamic.expression.analysis.element;

import com.joutvhu.dynamic.expression.analysis.match.DefaultMatcher;

public abstract class ElementAnalyzer<E> {
    public abstract void analysis(DefaultMatcher<E> matcher);
}
