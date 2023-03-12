package com.joutvhu.expansy.element;

import com.joutvhu.expansy.exception.ExpansyException;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.List;

public class NodeCache<E> {
    private final HashMap<NodeKey, NodeResult> cache = new HashMap<>();

    public void put(int offset, Element<E> element, List<NodeImpl<E>> nodes) {
        synchronized (this) {
            cache.computeIfAbsent(new NodeKey(offset, element), nodeKey -> new NodeResult(nodes));
        }
    }

    public void put(int offset, Element<E> element, ExpansyException exception) {
        synchronized (this) {
            cache.computeIfAbsent(new NodeKey(offset, element), nodeKey -> new NodeResult(exception));
        }
    }

    public List<NodeImpl<E>> get(int offset, Element<E> element) {
        synchronized (this) {
            NodeResult result = cache.get(new NodeKey(offset, element));
            return result != null ? result.value() : null;
        }
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    class NodeKey {
        private int offset;
        private Element<E> element;
    }

    class NodeResult {
        private List<NodeImpl<E>> nodes;
        private ExpansyException exception;

        public NodeResult(List<NodeImpl<E>> nodes) {
            this.nodes = nodes;
        }

        public NodeResult(ExpansyException exception) {
            this.exception = exception;
        }

        public List<NodeImpl<E>> value() {
            if (nodes != null)
                return nodes;
            throw exception;
        }
    }
}
