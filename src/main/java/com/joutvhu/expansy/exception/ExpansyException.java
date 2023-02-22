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

    public ExpansyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
