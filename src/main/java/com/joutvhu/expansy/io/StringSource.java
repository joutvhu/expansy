package com.joutvhu.expansy.io;

public class StringSource implements Source {
    private long next;
    private final StringBuffer buffer;

    public StringSource(String value) {
        this.next = 0;
        this.buffer = new StringBuffer(value);
    }

    @Override
    public long back(long offset) {
        next = Math.min(buffer.length(), offset);
        return next;
    }

    @Override
    public String read(int length) {
        int end = (int) Math.min(buffer.length(), next + length);
        return buffer.substring((int) next, end);
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
