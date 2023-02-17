package com.joutvhu.expansy.io;

public class StringSource implements Source {
    private long current;
    private final StringBuffer buffer;

    public StringSource(String value) {
        this.current = 0;
        this.buffer = new StringBuffer(value);
    }

    @Override
    public void reset() {
        current = 0;
    }

    @Override
    public long back(long offset) {
        current = Math.min(buffer.length(), offset);
        return current;
    }

    @Override
    public String read(int length) {
        int end = (int) Math.min(buffer.length(), current + length);
        String result = buffer.substring((int) current, end);
        current += result.length();
        return result;
    }

    @Override
    public String read(long offset, int length) {
        return buffer.substring((int) offset, (int) (offset + length));
    }
}
