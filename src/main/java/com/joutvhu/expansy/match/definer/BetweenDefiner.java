package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.Consumer;
import com.joutvhu.expansy.parser.ExpansyParser;

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
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                List<Matcher<E>> bm = between.matchers();
                int offset = consumer.offset();
                int r = 0;
                for (int i = 0; true; i++) {
                    boolean p = (i & 1) == 0;
                    try {
                        ExpansyParser<E> parser = consumer.state().getParser();
                        Params results = parser.parse(p ? matchers : bm, offset);
                        offset = results.getEnd();
                        if (p) {
                            r++;
                            if (minRepetitions == null || minRepetitions <= r)
                                consumer.stack(offset);
                            if (maxRepetitions != null && maxRepetitions >= r)
                                consumer.close();
                        }
                    } catch (Exception e) {
                        consumer.error(e.getMessage());
                    }
                }
            }
        };
    }

    public IsDefiner<E, T> is() {
        between = new IsDefiner<>(parent);
        return between;
    }

    public class IsDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, IsDefiner<E, T>> {
        private T parent;

        public IsDefiner(T parent) {
            this.parent = parent;
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
