package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.parser.ExpansyParser;

import java.util.Arrays;
import java.util.List;

public class Expansy<E> {
    private ElementRegister<E> register;

    private Expansy() {
    }

    public static <E> Expansy<E> instance() {
        return new Expansy<>();
    }

    public Expansy<E> setRegister(ElementRegister<E> register) {
        if (register != null)
            this.register = register;
        return this;
    }

    public Expansy<E> register(Element<E> element) {
        this.register.register(element);
        return this;
    }

    public ExpansyParser<E> selectAll() {
        return new ExpansyParser<>(register, null);
    }

    public ExpansyParser<E> select(String name) {
        return new ExpansyParser<>(register, List.of(name));
    }

    public ExpansyParser<E> select(String... names) {
        return new ExpansyParser<>(register, Arrays.asList(names));
    }

    public ExpansyParser<E> select(List<String> names) {
        return new ExpansyParser<>(register, names);
    }
}
