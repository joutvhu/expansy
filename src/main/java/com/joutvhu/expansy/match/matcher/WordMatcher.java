package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.filter.LinearFilter;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.filter.StopPoint;

public class WordMatcher<E> extends Matcher<E> {
    public WordMatcher(Definer<E> parent) {
        super(parent);
    }

    public WordMatcher(String name, Definer<E> parent) {
        super(name, parent);
    }

    @Override
    public void match(LinearFilter<E> filter) {
        while (true) {
            StopPoint point = filter.next();
            if (point == null) break;
            char c = point.getCharacter();
            if (c == '_' || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9'))
                filter.push();
            else
                filter.error("");
        }
    }
}