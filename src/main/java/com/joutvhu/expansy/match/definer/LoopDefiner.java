package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Params;
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
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                Params<E> params = new Params<>();
                params.setStart(consumer.offset());
                params.setEnd(consumer.offset());
                params.setValue("");
                if (minRepetitions == 0) {
                    consumer.push(params);
                    params = params.clone();
                }
                for (int i = 0; true; i++) {
                    try {
                        InternalParser<E> parser = consumer.state().getParser();
                        Params<E> results = parser.parseByMatchers(matchers, params.getEnd(), consumer.branch());
                        params.setValue(params.getValue().concat(results.getValue()));
                        params.addAll(results);
                        params.setEnd(results.getEnd());
                        if (minRepetitions == null || minRepetitions <= i){
                            consumer.push(params);
                            params = params.clone();
                        }
                        if (maxRepetitions != null && maxRepetitions >= i)
                            consumer.close();
                    } catch (Exception e) {
                        consumer.error(e.getMessage());
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
