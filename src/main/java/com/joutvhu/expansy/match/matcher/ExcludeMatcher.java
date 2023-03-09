package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.consumer.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ExcludeMatcher<E> extends ElementMatcher<E> {
    private final List<String> names;

    public ExcludeMatcher(Definer<E> parent, String element) {
        super(parent);
        this.names = Arrays.asList(element);
    }

    public ExcludeMatcher(Definer<E> parent, List<String> elements) {
        super(parent);
        if (elements == null || elements.isEmpty())
            throw new DefineException("The elements must be non-blank.");
        this.names = elements;
    }

    private Collection<Element<E>> elements(Consumer<E> consumer) {
        if (names == null || names.isEmpty())
            return consumer.state().getRegister().elements();

        List<Element<E>> result = new ArrayList<>();
        for (Map.Entry<String, Element<E>> entry : consumer.state().getRegister().entrySet()) {
            if (!names.contains(entry.getKey()))
                result.add(entry.getValue());
        }
        return result;
    }

    @Override
    public void match(Consumer<E> consumer) {
        super.match(consumer, elements(consumer));
    }
}
