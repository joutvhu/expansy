package com.joutvhu.dynamic.expression.analysis.element;

import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public abstract class ElementAnalyzer<E> {
    public abstract void analysis(Matcher<E> matcher);
}
