package com.joutvhu.dynamic.expression.analysis.match;

public class StopPoint {
    private String value;
    private char character;
    private int index;

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

    public char getCharacter() {
        return character;
    }

    public int getIndex() {
        return index;
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    public StopPoint buildNext(char character) {
        return new StopPoint(value + character, character, index + 1);
    }
}
