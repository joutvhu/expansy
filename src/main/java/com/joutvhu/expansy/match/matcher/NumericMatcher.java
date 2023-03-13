package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;

@SuppressWarnings("java:S3776")
public class NumericMatcher<E> extends Matcher<E> {
    public NumericMatcher(Definer<E> parent) {
        super(parent);
    }

    @Override
    public void match(Consumer<E> consumer) {
        boolean started = false;
        boolean decimal = false;
        boolean negative = false;
        while (true) {
            StopPoint point = consumer.next();
            if (point == null) return;
            if (point.getCharacter() == '-') {
                if (negative || started)
                    consumer.error("The minus sign appears more than once in a number.");
                negative = true;
                continue;
            }
            if ('0' <= point.getCharacter() && point.getCharacter() <= '9') {
                started = true;
                consumer.only();
                continue;
            }
            if (point.getCharacter() == '.') {
                if (!started)
                    consumer.error("The decimal point appears before the digits.");
                if (decimal)
                    consumer.error("The decimal point appears more than once in a number.");
                decimal = true;
                continue;
            }
        }
    }
}
