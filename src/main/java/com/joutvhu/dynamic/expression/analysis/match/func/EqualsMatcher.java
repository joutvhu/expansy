package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.filter.StopPoint;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EqualsMatcher<E> extends MatchFunction<E> {
    private List<String> values;

    public EqualsMatcher(Matcher<E> parent, String value) {
        super(parent);
        if (value == null || value.length() == 0)
            throw new IllegalArgumentException("");
        this.values = List.of(value);
    }

    public EqualsMatcher(Matcher<E> parent, List<String> values) {
        super(parent);
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
    public void match(LinearFilter filter) {
        int len = 0;
        StopPoint point = null;
        for (String value : values) {
            if (len != value.length())
                point = filter.next(value.length() - len);
            if (point == null) break;
            len = point.getValue().length();
            if (len != value.length()) {
                filter.error("");
                break;
            }
            if (value.equals(point.getValue()))
                filter.push();
        }
    }
}
