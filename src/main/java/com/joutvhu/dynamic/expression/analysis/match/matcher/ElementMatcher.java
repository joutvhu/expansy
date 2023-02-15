package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.element.Element;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Definer;

import java.util.Arrays;
import java.util.List;

public class ElementMatcher<E> extends Matcher<E> {
    private List<Element<E>> elements;

    public ElementMatcher(Definer<E> parent, Element<E> element) {
        super(parent);
        this.elements = Arrays.asList(element);
    }

    public ElementMatcher(Definer<E> parent, List<Element<E>> elements) {
        super(parent);
        this.elements = elements;
    }

    @Override
    public void match(LinearFilter filter) {
    }
}
