package com.joutvhu.dynamic.expression.analysis.element;

import java.util.Map;

public class ElementRegister<E> {
    private Map<String, Element<E>> elementMap;

    public void register(String name, Element<E> element) {
        this.elementMap.put(name, element);
    }

    public Element<E> get(String key) {
        return this.elementMap.get(key);
    }
}
