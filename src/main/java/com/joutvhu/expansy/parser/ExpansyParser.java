package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.io.BranchSelector;
import com.joutvhu.expansy.io.DefaultSelector;
import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.io.StringSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ExpansyParser<E> {
    private final ElementRegister<E> register;
    private final Collection<Element<E>> elements;
    private final BranchSelector selector;

    public ExpansyParser(ElementRegister<E> register, List<String> elements) {
        this(register, new DefaultSelector(), elements);
    }

    public ExpansyParser(ElementRegister<E> register, BranchSelector selector, List<String> elements) {
        this.register = register;
        this.selector = selector;
        this.elements = elements == null || elements.isEmpty() ? register.elements() : register.get(elements);
    }

    protected Analyser<E> analyser(Source source, Map<String, Object> model) {
        ExpansyState<E> state = new ExpansyState<>(source, register);
        if (model != null)
            state.putAll(model);
        return state.getAnalyser();
    }

    public List<Branch<E>> analysis(Source source, Map<String, Object> model) {
        Analyser<E> analyser = analyser(source, model);
        return analyser.analyse(elements);
    }

    public List<Branch<E>> analysis(Source source) {
        return analysis(source, null);
    }

    public List<Branch<E>> analysis(String value, Map<String, Object> model) {
        Source source = new StringSource(value);
        return analysis(source, model);
    }

    public List<Branch<E>> analysis(String value) {
        return analysis(value, null);
    }

    public E parseSingle(Source source, Map<String, Object> model) {
        List<Branch<E>> branches = analysis(source, model);
        branches = selector.order(branches);
        for (Branch<E> branch : branches) {
            if (branch.size() == 1) {
                Node<E> node = branch.get(0);
                return node.getElement().render(node);
            }
        }
        return null;
    }

    public E parseSingle(Source source) {
        return parseSingle(source, null);
    }

    public E parseSingle(String value, Map<String, Object> model) {
        Source source = new StringSource(value);
        return parseSingle(source, model);
    }

    public E parseSingle(String value) {
        return parseSingle(value, null);
    }

    public List<E> parse(Source source, Map<String, Object> model) {
        List<E> results = new ArrayList<>();
        List<Branch<E>> branches = analysis(source, model);
        Branch<E> branch = selector.select(branches);
        if (branch == null)
            return null;
        for (Node<E> node : branch) {
            E v = node.render();
            if (v != null) results.add(v);
        }
        return results;
    }

    public List<E> parse(Source source) {
        return parse(source, null);
    }

    public List<E> parse(String value, Map<String, Object> model) {
        Source source = new StringSource(value);
        return parse(source, model);
    }

    public List<E> parse(String value) {
        return parse(value, null);
    }
}
