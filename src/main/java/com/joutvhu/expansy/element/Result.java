package com.joutvhu.expansy.element;

import lombok.Getter;

import java.util.List;

@Getter
public class Result<E> {
    private List<Params<E>> values;

    public void add(Params<E> value) {
        values.add(value);
    }

    public void addAll(List<Params<E>> values) {
        values.addAll(values);
    }

    public Params<E> get(int index) {
        return values.get(index);
    }
}
