package com.joutvhu.expansy.element;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Branch<E> extends ArrayList<Params<E>> {
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
        branch.addAll(this);
        return branch;
    }
}
