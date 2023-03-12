package com.joutvhu.expansy.element;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

public class NodeCache<E> {
    private final HashMap<NodeKey, List<NodeImpl<E>>> cache = new HashMap<>();

    public void put(int offset, Element<E> element, List<NodeImpl<E>> nodes) {
        synchronized (this) {
            cache.put(new NodeKey(offset, element), nodes);
        }
    }

    public void putIfAbsent(int offset, Element<E> element, List<NodeImpl<E>> nodes) {
        synchronized (this) {
            cache.computeIfAbsent(new NodeKey(offset, element), nodeKey -> nodes);
        }
    }

    public List<NodeImpl<E>> get(int offset, Element<E> element) {
        synchronized (this) {
            return cache.get(new NodeKey(offset, element));
        }
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    class NodeKey {
        private int offset;
        private Element<E> element;
    }
}
