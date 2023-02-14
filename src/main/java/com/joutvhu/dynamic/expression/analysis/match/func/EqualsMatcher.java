package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.filter.StopPoint;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EqualsMatcher<E> extends MatchFunction<E> {
    private List<String> values;

    public EqualsMatcher(Matcher<E> parent, String value) {
        super(parent);
        this.values = Arrays.asList(value);
    }

    public EqualsMatcher(Matcher<E> parent, List<String> values) {
        super(parent);
        this.values = values;
        values.sort(Comparator.comparingInt(String::length));
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
