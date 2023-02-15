package com.joutvhu.expansy.parser;

import com.joutvhu.expansy.element.Element;

import java.io.IOException;
import java.io.Reader;

public class ExpansyParser<E> {
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
