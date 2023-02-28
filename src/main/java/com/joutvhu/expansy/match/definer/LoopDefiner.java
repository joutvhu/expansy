package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.ExpansyException;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.InternalParser;

import java.util.List;

public final class LoopDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, LoopDefiner<E, T>> {
    private T parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;

    public LoopDefiner(T parent) {
        this(parent, 0, null);
    }

    public LoopDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public LoopDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        this.minRepetitions = minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                Node<E> node = new Node<>();
                node.setStart(consumer.offset());
                node.setEnd(consumer.offset());
                node.setValue("");
                if (minRepetitions == 0) {
                    consumer.push(node);
                    node = node.clone();
                }
                for (int i = 0; true; i++) {
                    try {
                        InternalParser<E> parser = consumer.state().getParser();
                        Node<E> results = parser.parseByMatchers(matchers, node.getEnd(), consumer.branch());
                        node.setValue(node.getValue().concat(results.getValue()));
                        node.addAll(results);
                        node.setEnd(results.getEnd());
                        if (minRepetitions == null || minRepetitions == 0 || minRepetitions <= i){
                            consumer.push(node);
                            node = node.clone();
                        }
                        if (maxRepetitions != null && maxRepetitions >= i)
                            consumer.close();
                    } catch (MatchException e) {
                        consumer.errorAt(e.getMessage(), e.getIndex(), e.getContent());
                    } catch (ExpansyException e) {
                        consumer.errorAt(e.getMessage(), null, null);
                    }
                }
            }
        };
    }

    @Override
    public OrDefiner<E, LoopDefiner<E, T>> or() {
        return (OrDefiner<E, LoopDefiner<E, T>>) super.or();
    }

    public T end() {
        return parent;
    }
}
