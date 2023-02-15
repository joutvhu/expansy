package com.joutvhu.dynamic.expression.analysis.parser;

import com.joutvhu.dynamic.expression.analysis.element.Element;

import java.util.Map;

public class ExpressionRegister<E> {
    private Map<String, Element<E>> elementMap;

    public void register(String name, Element<E> element) {
        this.elementMap.put(name, element);
    }

    public Element<E> get(String key) {
        return this.elementMap.get(key);
    }
}
