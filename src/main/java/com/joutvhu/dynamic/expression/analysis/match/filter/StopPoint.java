package com.joutvhu.dynamic.expression.analysis.match.filter;

public class StopPoint {
    private String value;
    private Character character;
    private int index;

    public StopPoint() {
        this.value = "";
        this.character = null;
        this.index = 0;
    }

    public StopPoint(String value, char character) {
        this.character = character;
        this.value = value != null ? value : "";
        this.index = this.value.length() - 1;
        if (this.value.charAt(this.index) != character) {
            this.value += character;
            this.index++;
        }
    }

    StopPoint(String value, char character, int index) {
        this.value = value;
        this.character = character;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public int getLength() {
        return value.length();
    }

    public char getCharacter() {
        return character;
    }

    public int getIndex() {
        return index;
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    StopPoint next(String s) {
        value = value + s;
        character = s.charAt(s.length() - 1);
        index += s.length();
        return this;
    }

    StopPoint buildNext(String s) {
        return new StopPoint(value + s, s.charAt(s.length() - 1), index + s.length());
    }
}
