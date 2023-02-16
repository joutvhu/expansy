package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.io.Source;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ExpansyState<E> {
    private Source source;
    private ElementRegister<E> register;
    @Setter
    private ExpansyParser<E> parser;
    private Map<String, Object> shared;

    public ExpansyState(Source source, ElementRegister<E> register) {
        this.source = source;
        this.register = register;
        this.shared = new HashMap<>();
    }

    private ExpansyState(Source source, ElementRegister<E> register, ExpansyParser<E> parser) {
        this.source = source;
        this.register = register;
        this.parser = parser;
        this.shared = new HashMap<>();
    }

    public ExpansyState<E> copyWith(Source source) {
        return new ExpansyState<>(source, register, parser);
    }
}
