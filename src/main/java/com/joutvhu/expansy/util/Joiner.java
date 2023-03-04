package com.joutvhu.expansy.util;

public final class Joiner {
    private final CharSequence delimiter;

    private Joiner(CharSequence delimiter) {
        this.delimiter = delimiter;
    }

    public static Joiner on(CharSequence delimiter) {
        return new Joiner(delimiter);
    }

    public String join(char[] chars) {
        StringBuilder sb = new StringBuilder();
        boolean addDelimiter = false;
        for (char c : chars) {
            if (addDelimiter)
                sb.append(delimiter);
            sb.append(c);
            addDelimiter = delimiter != null;
        }
        return sb.toString();
    }
}
