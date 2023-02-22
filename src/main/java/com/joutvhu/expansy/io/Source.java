package com.joutvhu.expansy.io;

public final class Source {
    private String value;

    public Source(String value) {
        this.value = value;
    }

    public int length() {
        return value.length();
    }

    public String read(int offset, int length) {
        if (offset < length()) {
            int end = Math.min(offset + length, value.length());
            return value.substring(offset, end);
        }
        return "";
    }
}
