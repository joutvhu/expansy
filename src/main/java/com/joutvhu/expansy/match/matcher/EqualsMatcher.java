package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.filter.LinearConsumer;
import com.joutvhu.expansy.match.filter.StopPoint;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EqualsMatcher<E> extends Matcher<E> {
    private boolean ignoreCase;
    private List<String> values;

    public EqualsMatcher(Definer<E> parent, String value) {
        this(parent, value, false);
    }

    public EqualsMatcher(Definer<E> parent, String value, boolean ignoreCase) {
        super(parent);
        this.ignoreCase = ignoreCase;
        if (value == null || value.length() == 0)
            throw new IllegalArgumentException("");
        this.values = List.of(value);
    }

    public EqualsMatcher(Definer<E> parent, List<String> values) {
        this(parent, values, false);
    }

    public EqualsMatcher(Definer<E> parent, List<String> values, boolean ignoreCase) {
        super(parent);
        this.ignoreCase = ignoreCase;
        if (values == null || values.isEmpty())
            throw new IllegalArgumentException("");
        this.values = values.stream()
                .sorted(Comparator.comparingInt(String::length))
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (this.values.isEmpty())
            throw new IllegalArgumentException("");
    }

    @Override
    public void match(LinearConsumer<E> consumer) {
        int len = 0;
        StopPoint point = null;
        for (String value : values) {
            if (len != value.length())
                point = consumer.next(value.length() - len);
            if (point == null) break;
            len = point.getLength();
            if (len != value.length())
                consumer.error("");
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
