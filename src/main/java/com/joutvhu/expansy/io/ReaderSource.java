package com.joutvhu.expansy.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderSource implements Source {
    private long current;
    private final Reader reader;

    public ReaderSource(Reader reader) {
        this.current = 0;
        this.reader = new BufferedReader(reader);
    }

    @Override
    public void reset() {
        try {
            reader.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long back(long offset) {
        try {
            if (current != offset) {
                if (offset > current) {
                    long skipped = reader.skip(offset - current);
                    current += skipped;
                } else {
                    reader.reset();
                    current = reader.skip(offset);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return current;
    }

    @Override
    public String read(int length) {
        try {
            char[] characters = new char[length];
            int len = reader.read(characters);
            current += len;
            return String.valueOf(characters, 0, len);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String read(long offset, int length) {
        long backup = this.current;
        back(offset);
        String result = read(length);
        back(backup);
        return result;
    }
}
