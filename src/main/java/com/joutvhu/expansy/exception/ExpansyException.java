package com.joutvhu.expansy.exception;

public class ExpansyException extends RuntimeException {
    public ExpansyException() {
    }

    public ExpansyException(String message) {
        super(message);
    }

    public ExpansyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpansyException(Throwable cause) {
        super(cause);
    }

    public static ExpansyException of(Exception e) {
        if (e instanceof ExpansyException)
            return (ExpansyException) e;
        else
            return new ExpansyException(e);
    }
}
