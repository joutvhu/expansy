package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.Result;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.Consumer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class RegisteredMatcher<E> extends Matcher<E> {
    private List<String> names;

    public RegisteredMatcher(Definer<E> parent) {
        super(parent);
        this.names = null;
    }

    public RegisteredMatcher(Definer<E> parent, String element) {
        super(parent);
        this.names = Arrays.asList(element);
    }

    public RegisteredMatcher(Definer<E> parent, List<String> elements) {
        super(parent);
        this.names = elements;
    }

    @Override
    public void match(Consumer<E> consumer) {
        Collection<Element<E>> elements = names == null || names.isEmpty() ?
                consumer.state().getRegister().elements() :
                consumer.state().getRegister().get(names);
        List<Result<E>> results = consumer.state().getParser().parseByElements(elements, consumer);
        for (Result<E> result : results) {
            consumer.stack(result.getLength());
        }
        consumer.close();
    }
}
