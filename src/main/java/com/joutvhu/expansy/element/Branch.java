package com.joutvhu.expansy.element;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class Branch<E> extends ArrayList<Params<E>> {
    private Map<String, Object> shared;
    private Map<Integer, Set<Element<E>>> checking;

    public Branch() {
        shared = new HashMap<>();
        checking = new HashMap<>();
    }

    public void start(int index, Element<E> element) {
        Set<Element<E>> elements = checking.get(index);
        if (elements == null) {
            elements = new HashSet<>();
            checking.put(index, elements);
        }
        elements.add(element);
    }

    public boolean started(int index, Element<E> element) {
        Set<Element<E>> elements = checking.get(index);
        if (elements == null)
            return false;
        return elements.contains(element);
    }

    public void complete(int index, Element<E> element) {
        Set<Element<E>> elements = checking.get(index);
        if (elements != null)
            elements.remove(element);
    }

    public <P> void set(String key, P value) {
        shared.put(key, value);
    }

    public <P> P get(String key) {
        return (P) shared.get(key);
    }

    public Params<E> first() {
        int len = size();
        return len > 0 ? get(0) : null;
    }

    public Params<E> last() {
        int len = size();
        return len > 0 ? get(len - 1) : null;
    }

    public void replace(int index, Params<E> params) {
        boolean shouldRemove = true;
        if (index >= size()) {
            index = size();
            shouldRemove = false;
        }
        add(index, params);
        if (shouldRemove)
            remove(index + 1);
    }

    public void replaceFirst(Params<E> params) {
        replace(0, params);
    }

    public void replaceLast(Params<E> params) {
        replace(size() - 1, params);
    }

    @Override
    public Branch<E> clone() {
        Branch<E> branch = new Branch<>();
        branch.shared.putAll(shared);
        checking.forEach((index, elements) -> branch.checking.put(index, new HashSet<>(elements)));
        branch.addAll(this);
        return branch;
    }
}
