package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.parser.InternalParser;

import java.util.List;

public final class LoopDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, MaybeDefiner<E, T>> {
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
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                int offset = consumer.offset();
                for (int i = 0; true; i++) {
                    try {
                        InternalParser<E> parser = consumer.state().getParser();
                        Params results = parser.parseByMatchers(matchers, offset);
                        offset = results.getEnd();
                        if (minRepetitions == null || minRepetitions <= i)
                            consumer.stack(offset);
                        if (maxRepetitions != null && maxRepetitions >= i)
                            consumer.close();
                    } catch (Exception e) {
                        consumer.error(e.getMessage());
                    }
                }
            }
        };
    }

    public T end() {
        return parent;
    }
}
