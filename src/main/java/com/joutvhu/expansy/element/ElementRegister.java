package com.joutvhu.expansy.element;

import com.joutvhu.expansy.exception.DuplicateException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to register elements with a specified name.
 *
 * @author Giao Ho
 * @since 1.0.0
 */
public class ElementRegister<E> extends HashMap<String, Element<E>> {
    public void register(Element<E> element) {
        register(element.name(), element);
    }

    public void register(String name, Element<E> element) {
        if (this.containsKey(name))
            throw new DuplicateException("The element has {0} name has been registered.", name);
        this.put(name, element);
    }

    public Collection<Element<E>> get(String... keys) {
        return get(Arrays.asList(keys));
    }

    public Collection<Element<E>> get(List<String> keys) {
        List<Element<E>> result = new ArrayList<>();
        for (Map.Entry<String, Element<E>> entry : entrySet()) {
            if (keys.contains(entry.getKey()))
                result.add(entry.getValue());
        }
        return result;
    }

    public Collection<Element<E>> elements() {
        return values();
    }
}
