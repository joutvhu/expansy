package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;

import java.util.Collection;
import java.util.List;

abstract class ElementMatcher<E> extends Matcher<E> {
    public ElementMatcher(Definer<E> parent) {
        super(parent);
    }

    public void match(Consumer<E> consumer, Collection<Element<E>> elements) {
        Branch<E> branch = consumer.branch();
        if (branch != null) {
            Params<E> params = branch.last();
            if (params != null) {
                Element<E> element = params.getElement();
                if (element != null) {
                    for (Element<E> type : elements) {
                        if (type.getClass().isInstance(element) && element.name().equals(type.name())) {
                            consumer.push(params);
                            consumer.close();
                        }
                    }
                }
            }
        }

        List<Params<E>> results = consumer.state().getParser().parseByElements(elements, consumer);
        for (Params<E> result : results) {
            consumer.stack(result.getLength());
        }
        consumer.close();
    }
}
