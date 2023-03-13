package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;
import com.joutvhu.expansy.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("java:S3776")
public class EqualsMatcher<E> extends Matcher<E> {
    private final boolean ignoreCase;
    private final List<String> values;

    public EqualsMatcher(Definer<E> parent, String value) {
        this(parent, value, false);
    }

    public EqualsMatcher(Definer<E> parent, String value, boolean ignoreCase) {
        super(parent);
        this.ignoreCase = ignoreCase;
        if (value == null || value.length() == 0)
            throw new IllegalArgumentException("");
        this.values = Arrays.asList(value);
    }

    public EqualsMatcher(Definer<E> parent, List<String> values) {
        this(parent, values, false);
    }

    public EqualsMatcher(Definer<E> parent, List<String> values, boolean ignoreCase) {
        super(parent);
        this.ignoreCase = ignoreCase;
        if (values == null || values.isEmpty())
            throw new DefineException("The values must be non-blank.");
        this.values = values.stream()
                .sorted(Comparator.comparingInt(String::length))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (this.values.isEmpty())
            throw new IllegalArgumentException("");
    }

    @Override
    @SuppressWarnings("java:S2259")
    public void match(Consumer<E> consumer) {
        int len = 0;
        StopPoint point = null;
        for (String value : values) {
            if (len != value.length())
                point = consumer.next(value.length() - len);
            if (point == null)
                consumer.error("Does not match with \"{1}\"", value);
            len = point.getLength();
            if (len != value.length())
                consumer.error("The \"{0}\" does not match with \"{1}\"", point.getValue(), value);
            if (ignoreCase) {
                if (value.equalsIgnoreCase(point.getValue()))
                    consumer.push();
            } else {
                if (value.equals(point.getValue()))
                    consumer.push();
            }
        }
    }
}
