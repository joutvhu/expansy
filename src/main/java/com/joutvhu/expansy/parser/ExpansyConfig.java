package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.ElementRegister;
import com.joutvhu.expansy.io.Source;
import lombok.Getter;

@Getter
public class ExpansyConfig<E> {
    private Source source;
    private ElementRegister<E> register;
    private ExpansyParser<E> parser;
}
