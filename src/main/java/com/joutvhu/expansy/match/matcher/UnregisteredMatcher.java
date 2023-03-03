package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.consumer.Consumer;

import java.util.Arrays;
import java.util.List;

public class UnregisteredMatcher<E> extends ElementMatcher<E> {
    private List<Element<E>> elements;

    public UnregisteredMatcher(Definer<E> parent, Element<E> element) {
        super(parent);
        this.elements = Arrays.asList(element);
    }

    public UnregisteredMatcher(Definer<E> parent, List<Element<E>> elements) {
        super(parent);
        if (elements == null || elements.isEmpty())
            throw new DefineException("The elements must be non-blank.");
        this.elements = elements;
    }

    @Override
    public void match(Consumer<E> consumer) {
        super.match(consumer, elements);
    }
}
