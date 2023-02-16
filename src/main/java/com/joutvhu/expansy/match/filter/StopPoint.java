package com.joutvhu.expansy.match.filter;

import lombok.Getter;

@Getter
public class StopPoint {
    private String value;
    private long offset;

    public StopPoint(String value) {
        this(value, 0);
    }

    public StopPoint(String value, long offset) {
        this.value = value;
        this.offset = offset;
    }

    public int getLength() {
        return value.length();
    }

    public long getIndex() {
        return offset + value.length() - 1;
    }

    public char getCharacter() {
        return value.charAt(value.length() - 1);
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    StopPoint next(String s) {
        value = value.concat(s);
        return this;
    }
}
