package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;

public class SizeMatcher<E> extends Matcher<E> {
    private final int length;

    public SizeMatcher(Definer<E> parent, int length) {
        super(parent);
        this.length = length;
    }

    @Override
    public void match(Consumer<E> consumer) {
        StopPoint point = consumer.next(length);
        if (point == null)
            consumer.error("Not enough length.");
        consumer.complete();
    }
}
