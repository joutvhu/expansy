package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.filter.StopPoint;

public class NumericMatcher<E> extends Matcher<E> {
    public NumericMatcher(Definer<E> parent) {
        super(parent);
    }

    public NumericMatcher(String name, Definer<E> parent) {
        super(name, parent);
    }

    @Override
    public void match(LinearFilter<E> filter) {
        boolean started = false;
        boolean decimal = false;
        boolean negative = false;
        while (true) {
            StopPoint point = filter.next();
            if (point == null) return;
            if (point.getCharacter() == '-') {
                if (negative || started)
                    filter.error("");
                negative = true;
                continue;
            }
            if ('0' <= point.getCharacter() && point.getCharacter() <= '9') {
                started = true;
                filter.push();
                continue;
            }
            if (point.getCharacter() == '.') {
                if (!started)
                    filter.error("");
                if (decimal)
                    filter.error("");
                decimal = true;
                continue;
            }
        }
    }
}
