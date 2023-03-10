package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.Analyser;

import java.util.ArrayList;
import java.util.List;

public final class OrDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, OrDefiner<E, T>> {
    private final T parent;
    private final List<OrDefiner<E, T>> definers;

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
                MatchException error = null;
                Analyser<E> analyser = consumer.state().getAnalyser();
                for (OrDefiner<E, T> definer : definers) {
                    try {
                        List<Matcher<E>> matchers = definer.matchers();
                        List<Node<E>> nodes = analyser.analyseMatchers(matchers, consumer);
                        consumer.addForAll(nodes);
                    } catch (MatchException e) {
                        error = MatchException.or(error, e);
                    }
                }
                if (consumer.matched())
                    consumer.close();
                else if (error != null)
                    consumer.errorAt(error.getMessage(), error.getIndex(), error.getContent());
                else
                    consumer.error("Not match with all options.");
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
