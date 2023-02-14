package com.joutvhu.dynamic.expression.analysis;

import com.joutvhu.dynamic.expression.analysis.element.Element;

import java.io.IOException;
import java.io.Reader;

public class ExpressionParser<E> {
    public Element<E> parse(Reader reader) throws IOException {
        while (true) {
            char[] characters = new char[1];
            int len = reader.read(characters);
            if (len < 1) break;
            String value = String.valueOf(characters);
        }
        return null;
    }
}
