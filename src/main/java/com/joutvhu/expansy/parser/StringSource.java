package com.joutvhu.expansy.parser;

import java.io.IOException;
import java.io.Reader;

public class StringSource {
    private long next = 0;
    private final Reader reader;

    public StringSource(Reader reader) {
        this.reader = reader;
    }

    public boolean ready() throws IOException {
        return reader.ready();
    }

    public long back(long offset) throws IOException {
        if (next != offset) {
            if (offset > next) {
                long skipped = reader.skip(offset - next);
                next += skipped;
            } else {
                reader.reset();
                next = reader.skip(offset);
            }
        }
        return next;
    }

    public String read(int length) {
        try {
            char[] characters = new char[length];
            int len = reader.read(characters);
            next += len;
            return String.valueOf(characters, 0, len);
        } catch (IOException e) {
            return null;
        }
    }

    public String read(long offset, int length) {
        try {
            back(offset);
            return read(length);
        } catch (IOException e) {
            return null;
        }
    }

    public void close() throws IOException {
        reader.close();
    }
}
