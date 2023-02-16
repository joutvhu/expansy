package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.filter.StopPoint;

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
    public void match(LinearFilter<E> filter) {
        StopPoint point = minLength != null && minLength > 0 ? filter.next(minLength) : filter.next();
        if (point == null)
            return;
        do {
            if (pattern.matcher(point.getValue()).matches())
                filter.push();
            if (maxLength != null && point.getLength() >= maxLength)
                filter.complete();
            point = filter.next();
        } while (point != null);
    }
}