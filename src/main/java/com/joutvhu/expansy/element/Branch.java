package com.joutvhu.expansy.element;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public class Branch<E> extends ArrayList<Node<E>> {
    private Map<String, Object> shared;
    private Map<Integer, Set<Element<E>>> checking;

    public Branch() {
        shared = new HashMap<>();
        checking = new HashMap<>();
    }

    public List<E> render() {
        List<E> results = new ArrayList<>();
        for (Node<E> node : this) {
            E value = node.render();
            if (value != null)
                results.add(value);
        }
        return results;
    }

    /**
     * Highlight an Element being checked at the specified position.
     */
    public void start(int index, Element<E> element) {
        Set<Element<E>> elements = checking.get(index);
        if (elements == null) {
            elements = new HashSet<>();
            checking.put(index, elements);
        }
        elements.add(element);
    }

    /**
     * Checks if an element is checking at the specified position.
     */
    public boolean started(int index, Element<E> element) {
        Set<Element<E>> elements = checking.get(index);
        if (elements == null)
            return false;
        return elements.contains(element);
    }

    /**
     *
     */
    public void complete(int index, Element<E> element) {
        Set<Element<E>> elements = checking.get(index);
        if (elements != null)
            elements.remove(element);
    }

    /**
     * Set a value with key into the shared state of this branch.
     */
    public <P> void set(String key, P value) {
        shared.put(key, value);
    }

    /**
     * Get a value with key from the shared state of this branch.
     */
    public <P> P get(String key) {
        return (P) shared.get(key);
    }

    /**
     * Returns the first node in this branch.
     */
    public Node<E> first() {
        int len = size();
        return len > 0 ? get(0) : null;
    }

    /**
     * Returns the node at the specified position in this branch.
     */
    public Node<E> at(int index) {
        return get(index);
    }

    /**
     * Returns the last node in this branch.
     */
    public Node<E> last() {
        int len = size();
        return len > 0 ? get(len - 1) : null;
    }

    /**
     * Replace a node at the specified position with a new node
     */
    public void replace(int index, Node<E> node) {
        boolean shouldRemove = true;
        if (index < 0)
            index = 0;
        if (index >= size()) {
            index = size();
            shouldRemove = false;
        }
        add(index, node);
        if (shouldRemove)
            remove(index + 1);
    }

    /**
     * Replace first node with a new node
     */
    public void replaceFirst(Node<E> node) {
        replace(0, node);
    }

    /**
     * Replace last node with a new node
     */
    public void replaceLast(Node<E> node) {
        replace(size() - 1, node);
    }

    /**
     * Returns a copy of this branch.
     */
    @Override
    public Branch<E> clone() {
        Branch<E> branch = new Branch<>();
        branch.shared.putAll(shared);
        checking.forEach((index, elements) -> branch.checking.put(index, new HashSet<>(elements)));
        branch.addAll(this);
        return branch;
    }
}
