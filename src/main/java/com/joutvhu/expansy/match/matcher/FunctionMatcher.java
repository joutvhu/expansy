package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;

import java.util.function.Function;

public class FunctionMatcher<E> extends Matcher<E> {
    private Function<String, Boolean> checker;
    private Integer minLength;
    private Integer maxLength;

    public FunctionMatcher(Definer<E> parent, Function<String, Boolean> checker) {
        this(parent, checker, 1, null);
    }

    public FunctionMatcher(Definer<E> parent, Function<String, Boolean> checker, Integer length) {
        this(parent, checker, length, length);
    }

    public FunctionMatcher(Definer<E> parent, Function<String, Boolean> checker, Integer minLength, Integer maxLength) {
        super(parent);
        this.checker = checker;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public void match(Consumer<E> consumer) {
        StopPoint point = minLength != null && minLength > 0 ? consumer.next(minLength) : consumer.next();
        if (point == null)
            return;
        do {
            if (Boolean.TRUE.equals(checker.apply(point.getValue())))
                consumer.push();
            if (maxLength != null && point.getLength() >= maxLength)
                consumer.close();
            point = consumer.next();
        } while (point != null);
    }
}
