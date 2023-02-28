package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DefinerUtil {
    public <E> List<Matcher<E>> matchersOf(Element<E> element) {
        DefaultDefiner<E> definer = new DefaultDefiner<>();
        element.define(definer);
        return definer.matchers;
    }

    public <E> List<Matcher<E>> matchersOf(Definer<E> definer) {
        if (definer instanceof DefaultDefiner)
            return ((DefaultDefiner<E>) definer).matchers;
        if (definer instanceof ProxyDefiner)
            return ((ProxyDefiner<E, ?>) definer).matchers();
        return new ArrayList<>();
    }
}
