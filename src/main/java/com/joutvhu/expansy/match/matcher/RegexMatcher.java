package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.consumer.StopPoint;

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
    public void match(Consumer<E> consumer) {
        StopPoint point = minLength != null && minLength > 0 ? consumer.next(minLength) : consumer.next();
        if (point == null)
            return;
        do {
            if (pattern.matcher(point.getValue()).matches())
                consumer.stack();
            if (maxLength != null && point.getLength() >= maxLength)
                consumer.close();
            point = consumer.next();
        } while (point != null);
    }
}
