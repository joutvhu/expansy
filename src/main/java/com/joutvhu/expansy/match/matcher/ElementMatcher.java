package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Result;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;
import com.joutvhu.expansy.parser.ExpansyParser;

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
    public void match(LinearFilter<E> filter) {
        ExpansyParser<E> parser = filter.state().getParser();
        List<Result<E>> results = parser.checkElements(elements, filter);
        for (Result<E> result : results) {
            filter.push(result.getLength());
        }
        filter.close();
    }
}
