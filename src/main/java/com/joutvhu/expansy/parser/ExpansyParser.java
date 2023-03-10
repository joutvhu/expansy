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

public class ExpansyParser<E> {
    private ElementRegister<E> register;
    private Collection<Element<E>> elements;
    private BranchSelector selector;

    public ExpansyParser(ElementRegister<E> register, List<String> elements) {
        this(register, new DefaultSelector(), elements);
    }

    public ExpansyParser(ElementRegister<E> register, BranchSelector selector, List<String> elements) {
        this.register = register;
        this.selector = selector;
        this.elements = elements == null || elements.isEmpty() ? register.elements() : register.get(elements);
    }

    protected Analyser<E> analyser(Source source) {
        ExpansyState<E> state = new ExpansyState<>(source, register);
        return state.getAnalyser();
    }

    public List<Branch<E>> analysis(Source source) {
        Analyser<E> analyser = analyser(source);
        return analyser.analyse(elements);
    }

    public List<Branch<E>> analysis(String value) {
        Source source = new StringSource(value);
        return analysis(source);
    }

    public E parseSingle(Source source) {
        List<Branch<E>> branches = analysis(source);
        branches = selector.order(branches);
        for (Branch<E> branch : branches) {
            if (branch.size() == 1) {
                Node<E> node = branch.get(0);
                return node.getElement().render(node);
            }
        }
        return null;
    }

    public E parseSingle(String value) {
        Source source = new StringSource(value);
        return parseSingle(source);
    }

    public List<E> parse(Source source) {
        List<E> results = new ArrayList<>();
        List<Branch<E>> branches = analysis(source);
        Branch<E> branch = selector.select(branches);
        if (branch == null)
            return null;
        for (Node<E> node : branch) {
            E v = node.render();
            if (v != null) results.add(v);
        }
        return results;
    }

    public List<E> parse(String value) {
        Source source = new StringSource(value);
        return parse(source);
    }
}
