package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.io.Source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ExpansyParser<E> {
    private ElementRegister<E> register;
    private Collection<Element<E>> elements;

    public ExpansyParser(ElementRegister<E> register, List<String> elements) {
        this.register = register;
        this.elements = elements == null ? register.elements() : register.get(elements);
    }

    public E parseSingle(String value) {
        InternalParser<E> parser = parser(value);
        List<Branch<E>> branches = parser.parseByElements(elements);
        for (Branch<E> branch : branches) {
            if (branch.size() == 1) {
                Params<E> params = branch.get(0);
                return params.getElement().create(params);
            }
        }
        return null;
    }

    public List<E> parse(String value) {
        List<E> results = new ArrayList<>();
        InternalParser<E> parser = parser(value);
        List<Branch<E>> branches = parser.parseByElements(elements);
        branches.sort(Comparator.comparingInt(Branch::size));
        Branch<E> branch = branches.get(0);
        for (Params<E> params : branch) {
            E v = params.create();
            if (v != null) results.add(v);
        }
        return results;
    }

    protected InternalParser<E> parser(String value) {
        Source source = new Source(value);
        ExpansyState<E> state = new ExpansyState<>(source, register);
        return new InternalParser<>(state);
    }
}
