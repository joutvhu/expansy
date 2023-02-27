package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.InternalParser;

import java.util.List;

public final class MaybeDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, MaybeDefiner<E, T>> {
    private T parent;

    public MaybeDefiner(T parent) {
        this.parent = parent;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this, name) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                consumer.push();
                if (!matchers.isEmpty()) {
                    try {
                        InternalParser<E> parser = consumer.state().getParser();
                        Node<E> results = parser.parseByMatchers(matchers, consumer);
                        consumer.push(results);
                    } catch (Exception e) {
                        consumer.error(e.getMessage());
                    }
                }
                consumer.close();
            }
        };
    }

    @Override
    public OrDefiner<E, MaybeDefiner<E, T>> or() {
        return (OrDefiner<E, MaybeDefiner<E, T>>) super.or();
    }

    public T end() {
        return parent;
    }
}
