package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.io.Source;

import java.util.Collection;
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
        parser.parseByElements(elements, 0);
        return null;
    }

    public List<E> parse(String value) {
        InternalParser<E> parser = parser(value);
        parser.parseByElements(elements, 0);
        return null;
    }

    protected InternalParser<E> parser(String value) {
        Source source = new Source(value);
        ExpansyState<E> state = new ExpansyState<>(source, register);
        return new InternalParser<>(state);
    }
}
