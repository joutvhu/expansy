package com.joutvhu.expansy.match.filter;

import lombok.Getter;

@Getter
public class StopPoint {
    private String value;
    private Character character;
    private int index;

    public StopPoint(String value) {
        assert (value != null);
        this.value = value;
        this.index = value.length() - 1;
        this.character = value.charAt(index);
    }

    public int getLength() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    StopPoint next(String s) {
        value = value.concat(s);
        index = value.length() - 1;
        character = value.charAt(index);
        return this;
    }
}
