package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.Params;
import com.joutvhu.expansy.element.Result;
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
        List<Result<E>> results = parser.parseByElements(elements, 0);
        for (Result<E> result : results) {
            if (result.size() == 1) {
                Params<E> params = result.get(0);
                return params.getElement().create(params);
            }
        }
        return null;
    }

    public List<E> parse(String value) {
        List<E> results = new ArrayList<>();
        InternalParser<E> parser = parser(value);
        List<Result<E>> list = parser.parseByElements(elements, 0);
        list.sort(Comparator.comparingInt(Result::size));
        Result<E> result = list.get(0);
        for (Params<E> params : result) {
            results.add(params.getElement().create(params));
        }
        return results;
    }

    protected InternalParser<E> parser(String value) {
        Source source = new Source(value);
        ExpansyState<E> state = new ExpansyState<>(source, register);
        return new InternalParser<>(state);
    }
}
