package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearConsumer;
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
    public void match(LinearConsumer<E> consumer) {
        boolean started = false;
        boolean decimal = false;
        boolean negative = false;
        while (true) {
            StopPoint point = consumer.next();
            if (point == null) return;
            if (point.getCharacter() == '-') {
                if (negative || started)
                    consumer.error("");
                negative = true;
                continue;
            }
            if ('0' <= point.getCharacter() && point.getCharacter() <= '9') {
                started = true;
                consumer.push();
                continue;
            }
            if (point.getCharacter() == '.') {
                if (!started)
                    consumer.error("");
                if (decimal)
                    consumer.error("");
                decimal = true;
                continue;
            }
        }
    }
}
