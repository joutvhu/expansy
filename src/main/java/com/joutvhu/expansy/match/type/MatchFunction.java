package com.joutvhu.expansy.match.type;

import com.joutvhu.expansy.match.consumer.Consumer;

@FunctionalInterface
public interface MatchFunction<E> {
    void match(Consumer<E> consumer);
}
