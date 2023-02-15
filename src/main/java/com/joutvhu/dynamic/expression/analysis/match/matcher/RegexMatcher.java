package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.filter.StopPoint;

import java.util.regex.Pattern;

public class RegexMatcher<E> extends Matcher<E> {
    private Pattern pattern;
    private Integer minLength;
    private Integer maxLength;

    public RegexMatcher(Definer<E> parent, String regex) {
        this(parent, regex, 0, 1, null);
    }

    public RegexMatcher(Definer<E> parent, String regex, int flags) {
        this(parent, regex, flags, 1, null);
    }

    public RegexMatcher(Definer<E> parent, String regex, int flags, int length) {
        this(parent, regex, flags, length, length);
    }

    public RegexMatcher(Definer<E> parent, String regex, int flags, Integer minLength, Integer maxLength) {
        this(parent, Pattern.compile(regex, flags), minLength, maxLength);
    }

    public RegexMatcher(Definer<E> parent, Pattern pattern, Integer minLength, Integer maxLength) {
        super(parent);
        this.pattern = pattern;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public void match(LinearFilter filter) {
        StopPoint point = minLength != null ? filter.next(minLength) : filter.next();
        if (point == null)
            return;
        do {
            if (pattern.matcher(point.getValue()).matches())
                filter.push();
            if (maxLength != null && point.getLength() >= maxLength) {
                filter.enough();
                return;
            }
            point = filter.next();
        } while (point != null);
    }
}
