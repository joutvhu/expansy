package com.joutvhu.expansy.match.consumer;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class StopPoint implements Serializable {
    private static final long serialVersionUID = -2160182568269344661L;

    private final int offset;
    private String value;

    public StopPoint(String value) {
        this(value, 0);
    }

    public StopPoint(String value, int offset) {
        this.value = value;
        this.offset = offset;
    }

    public int getLength() {
        return value.length();
    }

    public int getIndex() {
        return offset + value.length();
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
