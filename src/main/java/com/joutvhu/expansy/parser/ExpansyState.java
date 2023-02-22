package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.io.Source;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ExpansyState<E> {
    private Source source;
    private ElementRegister<E> register;
    private InternalParser<E> parser;
    private Map<String, Object> shared;

    public ExpansyState(Source source, ElementRegister<E> register) {
        this.source = source;
        this.register = register;
        this.shared = new HashMap<>();
    }

    void setParser(InternalParser<E> parser) {
        this.parser = parser;
    }
}
