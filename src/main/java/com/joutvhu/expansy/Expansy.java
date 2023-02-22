package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;

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

    public List<E> parse(String value) {
        return null;
    }
}
