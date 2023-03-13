package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Branch;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.StaticStructure;
import com.joutvhu.expansy.element.Structure;
import com.joutvhu.expansy.io.BranchSelector;
import com.joutvhu.expansy.io.DefaultSelector;
import com.joutvhu.expansy.io.Source;
import com.joutvhu.expansy.io.StringSource;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ExpansyParser<E> {
    private final ElementRegister<E> register;
    private final Structure<E> structure;
    private final BranchSelector selector;

    @Deprecated
    public ExpansyParser(ElementRegister<E> register, List<String> names) {
        this(register, new DefaultSelector(), names);
    }

    @Deprecated
    public ExpansyParser(ElementRegister<E> register, BranchSelector selector, List<String> names) {
        this(register, selector, ((Function<Object, Structure<E>>) o -> {
            Structure<E> structure = new StaticStructure<>(names);
            structure.setRegister(register);
            return structure;
        }).apply(null));
    }

    public ExpansyParser(ElementRegister<E> register, Structure<E> structure) {
        this(register, new DefaultSelector(), structure);
    }

    public ExpansyParser(ElementRegister<E> register, BranchSelector selector, Structure<E> structure) {
        this.register = register;
        this.selector = selector;
        this.structure = structure;
    }

    protected Analyser<E> analyser(Source source, Map<String, Object> model) {
        ExpansyState<E> state = new ExpansyState<>(source, register);
        if (model != null)
            state.putAll(model);
        return state.getAnalyser();
    }

    public List<Branch<E>> analysis(Source source, Map<String, Object> model) {
        Analyser<E> analyser = analyser(source, model);
        return analyser.analyse(structure);
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
            if (branch.size() == 1)
                return branch.first().render();
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
        List<Branch<E>> branches = analysis(source, model);
        Branch<E> branch = selector.select(branches);
        return branch != null ? branch.render() : null;
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
