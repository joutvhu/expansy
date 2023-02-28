package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.ExpansyException;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.InternalParser;

import java.util.List;

public final class BetweenDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, BetweenDefiner<E, T>> {
    private T parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;
    private IsDefiner<E, T> between;

    public BetweenDefiner(T parent) {
        this(parent, 0, null);
    }

    public BetweenDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public BetweenDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        this.minRepetitions = minRepetitions == null || minRepetitions < 0 ? 0 : minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this, name) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                List<Matcher<E>> bm = between.matchers();
                int r = 0;
                Node<E> node = new Node<>();
                node.setStart(consumer.offset());
                node.setEnd(consumer.offset());
                node.setValue("");
                if (minRepetitions == 0) {
                    consumer.push(node);
                    node = node.clone();
                }
                for (int i = 0; true; i++) {
                    boolean p = (i & 1) == 0;
                    try {
                        InternalParser<E> parser = consumer.state().getParser();
                        Node<E> results = parser.parseByMatchers(p ? matchers : bm, node.getEnd(), consumer.branch());
                        node.setValue(node.getValue().concat(results.getValue()));
                        node.addAll(results);
                        node.setEnd(results.getEnd());
                        if (p) {
                            r++;
                            if (minRepetitions == null || minRepetitions <= r) {
                                consumer.push(node);
                                node = node.clone();
                            }
                            if (maxRepetitions != null && maxRepetitions >= r)
                                consumer.close();
                        }
                    } catch (MatchException e) {
                        consumer.errorAt(e.getMessage(), e.getIndex(), e.getContent());
                    } catch (ExpansyException e) {
                        consumer.errorAt(e.getMessage(), null, null);
                    }
                }
            }
        };
    }

    public IsDefiner<E, T> is() {
        between = new IsDefiner<>(parent);
        return between;
    }

    @Override
    public OrDefiner<E, BetweenDefiner<E, T>> or() {
        return (OrDefiner<E, BetweenDefiner<E, T>>) super.or();
    }

    public class IsDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, IsDefiner<E, T>> {
        private T parent;

        public IsDefiner(T parent) {
            this.parent = parent;
        }

        @Override
        public OrDefiner<E, IsDefiner<E, T>> or() {
            return (OrDefiner<E, IsDefiner<E, T>>) super.or();
        }

        public T end() {
            return parent;
        }

        @Override
        public Matcher<E> matcher() {
            return null;
        }
    }
}
