package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Matcher;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DefinerUtil {
    public <E> List<Matcher<E>> matchersOf(Element<E> element) {
        DefaultDefiner<E> definer = new DefaultDefiner<>();
        element.define(definer);
        return definer.matchers;
    }
}
