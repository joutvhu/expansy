package com.joutvhu.expansy.io;

import java.io.IOException;
import java.io.Reader;

public class ReaderSource implements Source {
    private long next;
    private final Reader reader;

    public ReaderSource(Reader reader) {
        this.next = 0;
        this.reader = reader;
    }

    @Override
    public long back(long offset) {
        try {
            if (next != offset) {
                if (offset > next) {
                    long skipped = reader.skip(offset - next);
                    next += skipped;
                } else {
                    reader.reset();
                    next = reader.skip(offset);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return next;
    }

    @Override
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

    @Override
    public String read(long offset, int length) {
        back(offset);
        return read(length);
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
