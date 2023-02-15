package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;

public final class BetweenDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, BetweenDefiner<E, T>> {
    private T parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;

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
            public void match(LinearFilter filter) {

            }
        };
    }

    public IsDefiner<E, T> is() {
        return new IsDefiner<>(parent);
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
