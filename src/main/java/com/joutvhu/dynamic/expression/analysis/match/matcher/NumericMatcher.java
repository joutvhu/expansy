package com.joutvhu.dynamic.expression.analysis.match.matcher;

import com.joutvhu.dynamic.expression.analysis.match.filter.LinearFilter;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;
import com.joutvhu.dynamic.expression.analysis.match.Definer;
import com.joutvhu.dynamic.expression.analysis.match.filter.StopPoint;

public class NumericMatcher<E> extends Matcher<E> {
    public NumericMatcher(Definer<E> parent) {
        super(parent);
    }

    public NumericMatcher(String name, Definer<E> parent) {
        super(name, parent);
    }

    @Override
    public void match(LinearFilter filter) {
        boolean started = false;
        boolean decimal = false;
        boolean negative = false;
        while (true) {
            StopPoint point = filter.next();
            if (point == null) break;
            if (point.getCharacter() == '-') {
                if (negative || started) {
                    filter.error("");
                    break;
                }
                negative = true;
                continue;
            }
            if ('0' <= point.getCharacter() && point.getCharacter() <= '9') {
                started = true;
                filter.push();
                continue;
            }
            if (point.getCharacter() == '.') {
                if (!started) {
                    filter.error("");
                    break;
                }
                if (decimal) {
                    filter.error("");
                    break;
                }
                decimal = true;
                continue;
            }
        }
    }
}
