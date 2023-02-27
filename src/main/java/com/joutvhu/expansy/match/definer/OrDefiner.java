package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.InternalParser;

import java.util.ArrayList;
import java.util.List;

public class OrDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, OrDefiner<E, T>> {
    private T parent;
    private List<OrDefiner<E, T>> definers;

    public OrDefiner(T parent) {
        this.parent = parent;
        this.definers = new ArrayList<>();
        this.definers.add(this);
    }

    private OrDefiner(T parent, List<OrDefiner<E, T>> definers) {
        this.parent = parent;
        this.definers = definers;
        this.definers.add(this);
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                String error = null;
                InternalParser<E> parser = consumer.state().getParser();
                for (OrDefiner<E, T> definer : definers) {
                    try {
                        List<Matcher<E>> matchers = definer.matchers();
                        Node<E> results = parser.parseByMatchers(matchers, consumer);
                        consumer.stack(results);
                    } catch (Exception e) {
                        error = e.getMessage();
                    }
                }
                if (consumer.matched())
                    consumer.close();
                else
                    consumer.error(error);
            }
        };
    }

    @Override
    public OrDefiner<E, T> or() {
        return new OrDefiner<>(parent, definers);
    }

    public T end() {
        return parent;
    }
}
