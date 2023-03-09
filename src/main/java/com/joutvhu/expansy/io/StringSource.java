package com.joutvhu.expansy.io;

public final class StringSource implements Source {
    private String value;

    public StringSource(String value) {
        this.value = value;
    }

    @Override
    public int length() {
        return value.length();
    }

    @Override
    public String read(int offset, int length) {
        if (offset < length()) {
            int end = Math.min(offset + length, value.length());
            return value.substring(offset, end);
        }
        return "";
    }
}
