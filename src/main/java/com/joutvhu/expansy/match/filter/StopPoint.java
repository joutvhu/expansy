package com.joutvhu.expansy.match.filter;

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

    public String getValue() {
        return value;
    }

    public int getLength() {
        return value.length();
    }

    public char getCharacter() {
        return character;
    }

    public int getStart() {
        return start;
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
