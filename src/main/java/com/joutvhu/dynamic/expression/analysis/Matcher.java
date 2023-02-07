package com.joutvhu.dynamic.expression.analysis;

import java.util.function.Function;

public interface Matcher<E> {
    Matcher equals(String value);

    Matcher options(String... values);

    Matcher match(String regex);

    Matcher match(Function<String, Boolean> checker);

    Matcher is(ExpressionElement<E> element);

    Matcher is(ExpressionElement<E>... elements);
}
