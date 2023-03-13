package com.joutvhu.expansy.exception;

public class MatchException extends ExpansyException {
    private static final long serialVersionUID = -3504927316729367835L;

    private final Integer index;
    private final String content;

    public MatchException(String message) {
        this(message, null, null);
    }

    public MatchException(String message, Integer index, String content) {
        super(message);
        this.index = index;
        this.content = content;
    }

    public MatchException(String message, Throwable cause) {
        super(message, cause);
        this.index = null;
        this.content = null;
    }

    public Integer getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    public static MatchException of(Exception e) {
        if (e instanceof MatchException)
            return (MatchException) e;
        else
            return new MatchException(e.getMessage(), e);
    }

    public static MatchException or(MatchException e1, MatchException e2) {
        if (e2 == null)
            return e1;
        if (e1 == null)
            return e2;
        if (e2.getIndex() == null)
            return e1;
        if (e1.getIndex() == null)
            return e2;
        if (e1.getIndex() < e2.getIndex())
            return e2;
        return e1;
    }
}
