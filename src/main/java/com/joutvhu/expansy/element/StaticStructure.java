package com.joutvhu.expansy.element;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class StaticStructure<E> extends Structure<E> {
    private final List<String> names;
    private Collection<Element<E>> elements;

    @Override
    public Collection<Element<E>> next(Branch<E> branch) {
        if (elements == null) {
            elements = names == null || names.isEmpty() ? register.elements() : register.get(names);
        }
        return elements;
    }
}
