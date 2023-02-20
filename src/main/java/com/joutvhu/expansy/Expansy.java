package com.joutvhu.expansy;

import com.joutvhu.expansy.element.Element;
import com.joutvhu.expansy.element.ElementRegister;

import java.io.Reader;
import java.util.List;

public class Expansy<E> {
    private ElementRegister<E> register;

    public Expansy<E> setRegister(ElementRegister<E> register) {
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

    public List<E> parse(Reader reader) {
        return null;
    }
}
