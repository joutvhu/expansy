package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Result;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearConsumer;
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
    public void match(LinearConsumer<E> consumer) {
        ExpansyParser<E> parser = consumer.state().getParser();
        List<Result<E>> results = parser.checkElements(elements, consumer);
        for (Result<E> result : results) {
            consumer.stack(result.getLength());
        }
        consumer.close();
    }
}
