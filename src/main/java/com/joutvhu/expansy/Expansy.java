package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.element.StaticStructure;
import com.joutvhu.expansy.element.Structure;
import com.joutvhu.expansy.io.BranchSelector;
import com.joutvhu.expansy.io.DefaultSelector;
import com.joutvhu.expansy.parser.ExpansyParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expansy<E> {
    private ElementRegister<E> register;
    private BranchSelector selector;

    private Expansy() {
        this.register = new ElementRegister<>();
        this.selector = new DefaultSelector();
    }

    public static <E> Expansy<E> instance() {
        return new Expansy<>();
    }

    public Expansy<E> register(ElementRegister<E> register) {
        if (register != null)
            this.register = register;
        return this;
    }

    public Expansy<E> register(Element<E> element) {
        this.register.register(element);
        return this;
    }

    public Expansy<E> selector(BranchSelector selector) {
        if (selector != null)
            this.selector = selector;
        return this;
    }

    public ExpansyParser<E> use(Structure<E> structure) {
        structure.setRegister(register);
        return new ExpansyParser<>(register, selector, structure);
    }

    public ExpansyParser<E> use(List<String> names) {
        return use(new StaticStructure<>(names));
    }

    public ExpansyParser<E> use(String... names) {
        return use(Arrays.asList(names));
    }

    public ExpansyParser<E> useAll() {
        return use(new ArrayList<>());
    }
}
