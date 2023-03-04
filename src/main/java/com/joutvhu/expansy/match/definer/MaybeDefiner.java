package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.Analyser;

import java.util.List;

public final class MaybeDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, MaybeDefiner<E, T>> {
    private T parent;

    public MaybeDefiner(T parent) {
        this.parent = parent;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                consumer.push();
                if (!matchers.isEmpty()) {
                    try {
                        Analyser<E> analyser = consumer.state().getAnalyser();
                        Node<E> results = analyser.analyseMatchers(matchers, consumer);
                        consumer.push(results);
                    } catch (MatchException e) {
                        consumer.errorAt(e.getMessage(), e.getIndex(), e.getContent());
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
