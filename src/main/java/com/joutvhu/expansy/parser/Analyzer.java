package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class Analyzer<E, S> {
    protected ElementRegister<E> register;
    private List<String> selected;

    public Analyzer(ElementRegister<E> register) {
        this.register = register;
    }

    public Analyzer<E, S> selectAll() {
        selected = null;
        return this;
    }

    public Analyzer<E, S> select(String name) {
        selected = List.of(name);
        return this;
    }

    public Analyzer<E, S> select(String... names) {
        selected = Arrays.asList(names);
        return this;
    }

    public Analyzer<E, S> select(List<String> names) {
        selected = names;
        return this;
    }

    protected Collection<Element<E>> elements() {
        if (selected == null)
            return register.elements();
        return register.get(selected);
    }

    public abstract E analyzeSingle(S value);

    public abstract List<E> analyzeMany(S value);
}
