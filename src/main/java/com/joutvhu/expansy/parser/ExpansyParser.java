package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.io.BranchSelector;
import com.joutvhu.expansy.io.DefaultSelector;
import com.joutvhu.expansy.io.Source;

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
        this.elements = elements == null ? register.elements() : register.get(elements);
    }

    public List<Branch<E>> analysis(String value) {
        InternalParser<E> parser = parser(value);
        List<Branch<E>> branches = parser.parseByElements(elements);
        return branches;
    }

    public E parseSingle(String value) {
        List<Branch<E>> branches = analysis(value);
        branches = selector.order(branches);
        for (Branch<E> branch : branches) {
            if (branch.size() == 1) {
                Node<E> node = branch.get(0);
                return node.getElement().render(node);
            }
        }
        return null;
    }

    public List<E> parse(String value) {
        List<E> results = new ArrayList<>();
        List<Branch<E>> branches = analysis(value);
        Branch<E> branch = selector.select(branches);
        for (Node<E> node : branch) {
            E v = node.render();
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
