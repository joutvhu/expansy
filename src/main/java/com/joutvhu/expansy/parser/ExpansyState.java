package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.io.Source;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ExpansyState<E> {
    private final Source source;
    private final ElementRegister<E> register;
    private final Map<String, Object> shared;
    private final Analyser<E> analyser;

    public ExpansyState(Source source, ElementRegister<E> register) {
        this.source = source;
        this.register = register;
        this.shared = new HashMap<>();
        this.analyser = new ExpansyAnalyser<>(this);
    }

    public <T> T get(String key) {
        return (T) shared.get(key);
    }

    public <T> void set(String key, T value) {
        shared.put(key, value);
    }

    public int getLength() {
        return source.length();
    }
}
