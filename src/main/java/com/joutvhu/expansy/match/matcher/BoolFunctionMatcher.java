package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;

import java.util.function.Function;

public class BoolFunctionMatcher<E> extends Matcher<E> {
    private final Function<String, Boolean> checker;
    private final Integer minLength;
    private final Integer maxLength;

    public BoolFunctionMatcher(Definer<E> parent, Function<String, Boolean> checker) {
        this(parent, checker, 1, null);
    }

    public BoolFunctionMatcher(Definer<E> parent, Function<String, Boolean> checker, Integer length) {
        this(parent, checker, length, length);
    }

    public BoolFunctionMatcher(Definer<E> parent, Function<String, Boolean> checker, Integer minLength, Integer maxLength) {
        super(parent);
        if (checker == null)
            throw new DefineException("The checker must be non-null.");
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
            Boolean v = checker.apply(point.getValue());
            if (Boolean.TRUE.equals(v))
                consumer.push();
            if (Boolean.FALSE.equals(v))
                consumer.error("Cannot match value {0}", point.getValue());
            if (maxLength != null && point.getLength() >= maxLength)
                consumer.close();
            point = consumer.next();
        } while (point != null);
    }
}
