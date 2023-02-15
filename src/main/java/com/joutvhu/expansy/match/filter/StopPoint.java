package com.joutvhu.expansy.match.filter;

import lombok.Getter;

@Getter
public class StopPoint {
    private String value;
    private Character character;
    private int start;
    private int index;

    public StopPoint(String value) {
        this.start = 0;
        this.value = value != null ? value : "";
        this.index = this.value.length() - 1;
        this.character = this.value.charAt(this.index);
        if (this.value.charAt(this.index) != character) {
            this.value += character;
            this.index++;
        }
    }

    public StopPoint(String value, char character) {
        this.start = 0;
        this.value = value != null ? value : "";
        this.index = this.value.length() - 1;
        this.character = character;
        if (this.value.charAt(this.index) != character) {
            this.value += character;
            this.index++;
        }
    }

    StopPoint(String value, char character, int index) {
        this.start = index;
        this.value = value;
        this.character = character;
        this.index = index;
    }

    public int getLength() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    StopPoint back(int i) {
        value = value.substring(0, i + 1);
        index = i;
        character = value.charAt(i);
        return this;
    }

    StopPoint next(String s) {
        value = value.concat(s);
        index = value.length() - 1;
        character = value.charAt(index);
        return this;
    }

    StopPoint buildNext(String s) {
        String newValue = value.concat(s);
        int index = newValue.length() - 1;
        return new StopPoint(newValue, newValue.charAt(index), index);
    }
}
