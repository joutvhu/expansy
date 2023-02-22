package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.io.Source;

import java.util.List;

public class StringAnalyzer<E> extends Analyzer<E, String> {
    public StringAnalyzer(ElementRegister<E> register) {
        super(register);
    }

    @Override
    public E analyzeSingle(String value) {
        ExpansyParser<E> parser = parser(value);
        return null;
    }

    @Override
    public List<E> analyzeMany(String value) {
        ExpansyParser<E> parser = parser(value);
        return null;
    }

    private ExpansyParser<E> parser(String value) {
        Source source = new Source(value);
        ExpansyState<E> state = new ExpansyState<>(source, register);
        return new ExpansyParser<>(state);
    }
}