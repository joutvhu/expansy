package com.joutvhu.expansy.io;

public class StringSource implements Source {
    private long next;
    private String value;

    public StringSource(String value) {
        this.next = 0;
        this.value = value;
    }

    @Override
    public long back(long offset) {
        next = offset;
        return next;
    }

    @Override
    public String read(int length) {
        return value.substring((int) next, (int) next + length);
    }

    @Override
    public String read(long offset, int length) {
        back(offset);
        return read(length);
    }

    @Override
    public void close() {
    }
}
