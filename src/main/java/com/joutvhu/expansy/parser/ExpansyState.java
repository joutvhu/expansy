package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.NodeCache;
import com.joutvhu.expansy.element.NodeImpl;
import com.joutvhu.expansy.exception.ExpansyException;
import com.joutvhu.expansy.io.Source;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ExpansyState<E> {
    private final Source source;
    private final ElementRegister<E> register;
    private final Map<String, Object> shared;
    private final Analyser<E> analyser;
    private final NodeCache<E> cache;

    public ExpansyState(Source source, ElementRegister<E> register) {
        this.source = source;
        this.register = register;
        this.shared = new HashMap<>();
        this.analyser = new ExpansyAnalyser<>(this);
        this.cache = new NodeCache<>();
    }

    public void putToCache(int offset, Element<E> element, List<NodeImpl<E>> nodes) {
        if (cache != null)
            cache.put(offset, element, nodes);
    }

    public void putToCache(int offset, Element<E> element, ExpansyException exception) {
        if (cache != null)
            cache.put(offset, element, exception);
    }

    public List<NodeImpl<E>> getFromCache(int offset, Element<E> element) {
        if (cache != null)
            cache.get(offset, element);
        return null;
    }

    public <T> T get(String key) {
        return (T) shared.get(key);
    }

    public <T> void set(String key, T value) {
        shared.put(key, value);
    }

    void putAll(Map<String, Object> model) {
        shared.putAll(model);
    }

    public int length() {
        return source.length();
    }
}
